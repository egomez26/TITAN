/************************************************************************/
/* Access/CPN */
/* Copyright 2010-2011 AIS Group, Eindhoven University of Technology */
/*                                                                      */
/* This library is free software; you can redistribute it and/or */
/* modify it under the terms of the GNU Lesser General Public */
/* License as published by the Free Software Foundation; either */
/* version 2.1 of the License, or (at your option) any later version. */
/*                                                                      */
/* This library is distributed in the hope that it will be useful, */
/* but WITHOUT ANY WARRANTY; without even the implied warranty of */
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU */
/* Lesser General Public License for more details. */
/*                                                                      */
/* You should have received a copy of the GNU Lesser General Public */
/* License along with this library; if not, write to the Free Software */
/* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, */
/* MA 02110-1301 USA */
/************************************************************************/
package org.pnpl.solvers.cpntools.generators;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.cpntools.accesscpn.engine.highlevel.HighLevelSimulator;
import org.cpntools.accesscpn.engine.highlevel.MultiSetUtils;
import org.cpntools.accesscpn.engine.highlevel.instance.Binding;
import org.cpntools.accesscpn.engine.highlevel.instance.Instance;
import org.cpntools.accesscpn.engine.highlevel.instance.Marking;
import org.cpntools.accesscpn.engine.highlevel.instance.State;
import org.cpntools.accesscpn.engine.highlevel.instance.cpnvalues.CPNValue;
import org.cpntools.accesscpn.engine.proxy.ProxyDaemon;
import org.cpntools.accesscpn.engine.proxy.ProxySimulator;

import org.cpntools.accesscpn.model.Place;
import org.cpntools.accesscpn.model.PlaceNode;
import org.cpntools.accesscpn.model.Transition;

public class StateSpaceGenerator {
	public static Dictionary<String,Dictionary<String, String>> reachability = new Hashtable<String,Dictionary<String, String>>();
	
	public static Dictionary<Integer,String[]> unbounded_places = new Hashtable<Integer,String[]>();
	public static boolean report = true;
	public static String initialMarking="";
	public static List<String> visitedStrings;
	
	public static final class ComputeThread extends Thread {
		public final BlockingDeque<CompressedState> waiting;
		public CompressedStateSet storage;
		public final HighLevelSimulator simulator;
		public final Date start;
		public final List<Instance<Place>> allRealPlaceInstances;
		public final List<org.cpntools.accesscpn.engine.highlevel.instance.State> dead;
		
		//Represents the reachability graph
		//Keys are markings, and the value is the dict containing the enabled transitions
		//	and the markings resulting from applying them
		
		public ComputeThread(final BlockingDeque<CompressedState> waiting, final CompressedStateSet storage,
		        final List<Instance<Place>> allRealPlaceInstances, final HighLevelSimulator simulator,
		        final List<org.cpntools.accesscpn.engine.highlevel.instance.State> dead, final Date start) {
			this.waiting = waiting;
			this.storage = storage;
			this.allRealPlaceInstances = allRealPlaceInstances;
			this.simulator = simulator;
			this.dead = dead;
			this.start = start;
		}

		@Override
		public void run() {
			try {
				StateSpaceGenerator.generateStateSpace(simulator, storage, allRealPlaceInstances, waiting, dead, start);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static int THREADS = 1;
	public static Map<String, Long> profile = new HashMap<String, Long>();
	public static ProxySimulator ps;

	public static AtomicInteger threadCount;

	public static int arcs = 0;

	public static final BlockingQueue<CompressedState> toSerialize = new LinkedBlockingQueue<CompressedState>();
	public static final BlockingQueue<String> toSerializeBindings = new LinkedBlockingQueue<String>();
	public static final AtomicBoolean done = new AtomicBoolean(false);
	public static final boolean CHECKPOINTING = false;

	static public AtomicBoolean checkpointing = new AtomicBoolean(false);

	static public int last = -1;

	static int stateCount = 1;

	public static String getPathTo(String marking, String path) {
		List<String> q = new ArrayList<String>();
		
		q.add(path);
		
		while(!q.isEmpty()) {
			String current = q.get(0);
			q.remove(0);
			
			String[] path_split = current.split("-> ");
			String last_marking = path_split[path_split.length-1];
			
			if(last_marking.contains(marking) || marking.contains(last_marking)) return current;
			System.out.println(last_marking);
			
			if(StateSpaceGenerator.reachability.get(last_marking)!=null) {
				Enumeration<String> ekey = StateSpaceGenerator.reachability.get(last_marking).keys();
			    while(ekey.hasMoreElements()) {
			    	String k = ekey.nextElement();
			    	String dest = StateSpaceGenerator.reachability.get(last_marking).get(k);
			    	
			    	if(!current.contains(dest)) {
			    		q.add(String.format("%s [%s]-> %s", current, k, dest));
			    	}
			    }
			}
			
		}
		
		return "";
		
	}
	
	public static int findId(String placename) {
		Enumeration<String> ekey = StateSpaceGenerator.reachability.keys();
	    while(ekey.hasMoreElements()) {
	    	String k = ekey.nextElement();
	    	//The place name must be at least in the initial marking
	    	String [] k_split = k.split(", ");
	    	for(int i=0; i<k_split.length; i++) {
	    		if(k_split[i].contains(placename)) return i;
	    	}
	    }
		return -1;
	}
	
	public static String findReachablePlace(int placenumber) {
		Enumeration<String> ekey = StateSpaceGenerator.reachability.keys();
	    while(ekey.hasMoreElements()) {
	    	String k = ekey.nextElement();
	    	
	    	if(!k.split(", ")[placenumber].contains("empty")) {
	    		return k;
	    	}
	    	
	    	Enumeration<String> ekey2 = StateSpaceGenerator.reachability.get(k).keys();
	    	
	    	while(ekey2.hasMoreElements()) {
	    		String t = ekey2.nextElement();
	    		String dest = StateSpaceGenerator.reachability.get(k).get(t);
	    		if(!dest.split(", ")[placenumber].contains("empty")) {
	    			return dest;
	    		}
		    	
	    	}    
		     
	    }
		return "";
	}

	public static synchronized boolean add(final CompressedStateSet storage, final CompressedState c,
	        final CompressedState compressed, final Binding b, final List<Instance<Place>> allRealPlaceInstances) {
		if (storage.add(compressed)) {
			compressed.setStateNumber(StateSpaceGenerator.stateCount++);
			compressed.setPredecessor(c.getStateNumber());
			StateSpaceGenerator.serialize(null, "" + b, allRealPlaceInstances, compressed);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unused")
	public static void computeBounds(final HighLevelSimulator simulator, final CompressedStateSet storage,
	        final List<Instance<Place>> allRealPlaceInstances, final Map<Instance<? extends PlaceNode>, Integer> max,
	        final Map<Instance<? extends PlaceNode>, Integer> min,
	        final Map<Instance<? extends PlaceNode>, List<CPNValue>> msmax,
	        final Map<Instance<? extends PlaceNode>, List<CPNValue>> msmin) throws Exception {
		int i;
		HighLevelSimulator ss = simulator;
		System.out.print("[pnpl] Calculating bounds\n0:  ");
		final Date start = new Date();
		i = 0;
		final Map<String, List<CPNValue>> mscache = new HashMap<String, List<CPNValue>>();
		for (final CompressedState compressed : storage) {
			final State s = compressed.decompress(allRealPlaceInstances);
			Map<Instance<? extends PlaceNode>, List<CPNValue>> structuredMarking = null;
			while (structuredMarking == null) {
				try {
					structuredMarking = ss.getStructuredMarking(s, mscache);
				} catch (final Exception e) {
					ss = StateSpaceGenerator.ps.getSimulatorClone();
				}
			}
			for (final Entry<Instance<? extends PlaceNode>, List<CPNValue>> entry : structuredMarking.entrySet()) {
				max.put(entry.getKey(), Math.max(entry.getValue().size(),
				        max.get(entry.getKey()) == null ? 0 : max.get(entry.getKey())));
				min.put(entry.getKey(),
				        Math.min(entry.getValue().size(),
				                min.get(entry.getKey()) == null ? Integer.MAX_VALUE : min.get(entry.getKey())));
				msmin.put(entry.getKey(), MultiSetUtils.min(entry.getValue(), msmin.get(entry.getKey())));
				msmax.put(entry.getKey(), MultiSetUtils.max(entry.getValue(), msmax.get(entry.getKey())));
			}
			StateSpaceGenerator.handleEOL(start, ++i, storage.size());
		}
		StateSpaceGenerator.handleEOL(start, 0);
	}

	@SuppressWarnings("unused")
	static void generateStateSpace(HighLevelSimulator simulator, final CompressedStateSet storage,
	        final List<Instance<Place>> allRealPlaceInstances, final BlockingDeque<CompressedState> waiting,
	        final List<State> dead, final Date start) throws Exception, IOException {
		
		//List so markings can be repeated once
		List<Dictionary<Integer, Dictionary<String, Integer>>> visitedMarkings = 
				new LinkedList<Dictionary<Integer, Dictionary<String, Integer>>> ();
		List<String> visitedStrings = new LinkedList<String>();
		
		final List<Instance<Transition>> allTransitionInstances = simulator.getAllTransitionInstances();
		State previous = null, s = null;
		outer: while (true) {
			try {
				CompressedState c = null;
				StateSpaceGenerator.threadCount.decrementAndGet();
				while (c == null) {
					synchronized (StateSpaceGenerator.threadCount) {
						c = waiting.pollLast(1, TimeUnit.SECONDS);
						if (c == null) {
							System.out.print("P" + StateSpaceGenerator.threadCount.get());
							if (StateSpaceGenerator.threadCount.get() == 0) {
								break outer;
							}
						} else {
							StateSpaceGenerator.threadCount.incrementAndGet();
						}
					}
					Thread.yield();
				}
				s = c.decompress(allRealPlaceInstances);
				StateSpaceGenerator.profileStart();
				simulator.setMarkingFast(s, previous, true);
				StateSpaceGenerator.profileEnd("setMarking");
				previous = s;
				// EG: los comentarios justo de debajo hay que quitarlos cuando arregle el simulator
//				final List<Instance<? extends Transition>> enabled = simulator.isEnabled(allTransitionInstances);
				final List<Binding> bindings = new ArrayList<Binding>();
//				for (final Instance<? extends Transition> ti : enabled) {
//					StateSpaceGenerator.profileStart();
//					bindings.addAll(simulator.getBindings(ti));
//					StateSpaceGenerator.profileEnd("getBindings");
//				}
				if (bindings.isEmpty()) {
					dead.add(s);
					System.out.println("\nDead state (" + c.getStateNumber() + "):\n" + s);
				}
				StateSpaceGenerator.arcs += bindings.size();
				
				
				for (final Binding b : bindings) {
					
					String from = "";
					for(Marking ma : s.getAllMarkings()) {
						from += ma.getPlaceInstance().getNode().getName().getText()+":"+ma.getMarking()+", ";
					}
					
					if(checkAncestors(from, visitedStrings).length()>0) from = checkAncestors(from, visitedStrings);
					
					String with = b.getTransitionInstance().getNode().getName().asString();
				
					StateSpaceGenerator.profileStart();
					if (!simulator.execute(b)) { throw new Exception("Binding not found: " + b); }
					StateSpaceGenerator.profileEnd("execute");
					StateSpaceGenerator.profileStart();
					final State ss = simulator.getMarking(false);
					
					String to = "";
					for(Marking ma : ss.getAllMarkings()) {
						to += ma.getPlaceInstance().getNode().getName().getText()+":"+ma.getMarking()+", ";
					}
					
					//Check if state is a previous state + a constant
					Dictionary<Integer, Dictionary<String, Integer>> currentMarking = StateSpaceGenerator.getMarkingAsDict(ss);			    
					
					String str = findLinearComb(visitedStrings, visitedMarkings, currentMarking, from, with, to);
					
					boolean linear_comb= str.length()>0;
					
					
					if (linear_comb) {
						String previous_to = checkAncestors(to, visitedStrings);
						to = str;
						int num_w_to = 0, num_w_pre = 0;
						for(int cx=0; cx+1<to.length(); cx++) {
							if(to.charAt(cx)=='w' && to.charAt(cx+1)=='`' ) {
								num_w_to++;
							}
						}
						for(int cx=0; cx+1<previous_to.length(); cx++) {
							if(previous_to.charAt(cx)=='w' && previous_to.charAt(cx+1)=='`' ) {
								num_w_pre++;
							}
						}
						
						if(num_w_to <= num_w_pre) {
							to=previous_to;
						}

						
						
						str = checkAncestors(from, visitedStrings);
						if(str.length()>0) {
							
							if(StateSpaceGenerator.reachability.get(from)!=null) {
								Dictionary<String, String> foo = StateSpaceGenerator.reachability.get(from);
								foo.put(with, to);
								
								StateSpaceGenerator.reachability.remove(from);
								StateSpaceGenerator.reachability.put(str, foo);
								
							}
							else {
								StateSpaceGenerator.reachability.put(str, new Hashtable<String, String>());
								StateSpaceGenerator.reachability.get(str).put(with, to);	
							}
						}
						
						else if(StateSpaceGenerator.reachability.get(from)!=null) {
							StateSpaceGenerator.reachability.get(from).put(with, to);	
						}
						else {
							StateSpaceGenerator.reachability.put(from, new Hashtable<String, String>());
							StateSpaceGenerator.reachability.get(from).put(with, to);
						}
						
						//Expand each node once
						if( !visitedStrings.contains(to) ) {
							visitedStrings.add(to);
							linear_comb=false;
						}
						String ancestor_from=checkAncestors(from, visitedStrings);
						if(ancestor_from.length()>0 && !ancestor_from.equals(from)) {
							
							replaceWithAncestors(from, ancestor_from);
							
							from=ancestor_from;
						}
						String ancestor_to=checkAncestors(to, visitedStrings);
						if(ancestor_to.length()>0 && !ancestor_to.equals(to)) {
							
							replaceWithAncestors(to, ancestor_to);
							
							to=ancestor_to;
						}
						
					}
					

					if(!linear_comb) {
						if(StateSpaceGenerator.reachability.size()==0) {
							StateSpaceGenerator.initialMarking = from;
						}
						if(StateSpaceGenerator.reachability.get(from)==null) {
							StateSpaceGenerator.reachability.put(from, new Hashtable<String, String>());
						}
						StateSpaceGenerator.reachability.get(from).put(with, to);
					}

					
					visitedMarkings.add(currentMarking);
					
					StateSpaceGenerator.profileEnd("getMarking");
					final CompressedState compressed = new CompressedState(allRealPlaceInstances, ss);
					
					
					//If the state is linear combination of one of the previous do not add it
					if (!linear_comb && StateSpaceGenerator.add(storage, c, compressed, b, allRealPlaceInstances)) {
						waiting.add(compressed);
						final int size = storage.size();
						StateSpaceGenerator.handleEOL(start, size);
						if (size % 50 == 0) {
							System.out.print(StateSpaceGenerator.arcs + "  " + waiting.size() + "  "
							        + CompressedState.getValues().size() + "  ");
							// if (size % 200 == 0)
							// storage.printStats();
						}
					} else {
						boolean found = false;
						
					}
					StateSpaceGenerator.profileStart();
					simulator.setMarkingFast(s, ss, false);
					StateSpaceGenerator.profileEnd("setMarking");
				}

				if (storage.size() - StateSpaceGenerator.last > 10000 && StateSpaceGenerator.CHECKPOINTING) {
					if (!StateSpaceGenerator.checkpointing.getAndSet(true)) {
						System.out.print('W');
						final LinkedList<CompressedState> states = new LinkedList<CompressedState>();
						inner: while (true) {
							synchronized (StateSpaceGenerator.threadCount) {
								c = waiting.poll(50, TimeUnit.MILLISECONDS);
								if (c == null) {
									System.out.print(StateSpaceGenerator.threadCount.get());
									if (StateSpaceGenerator.threadCount.get() == 1) {
										StateSpaceGenerator.serialize(null, null, null, new CompressedState());
										break inner;
									}
								} else {
									states.add(c);
								}
							}
						}
						System.out.print('C');
						final ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(
						        new FileOutputStream(new File("checkpoint.dmp"))));
						outputStream.writeObject(CompressedState.getValues());
						outputStream.writeInt(states.size());
						for (final CompressedState cs : states) {
							outputStream.writeObject(cs);
						}
						outputStream.close();
						StateSpaceGenerator.last = storage.size();
						for (final CompressedState cs : states) {
							waiting.add(cs);
						}
						System.out.print('D');
						StateSpaceGenerator.checkpointing.set(false);
					}
				}
			} catch (final Exception e) {
				e.printStackTrace();
				simulator.destroy();
				if (s != null) {
					waiting.add(new CompressedState(allRealPlaceInstances, s));
				}
				previous = null;
				simulator = StateSpaceGenerator.ps.getSimulatorClone();
			}
		}
		StateSpaceGenerator.visitedStrings = visitedStrings;
	}
	public static String getStateAsString(State s) {
		String from = "";
		for(Marking ma : s.getAllMarkings()) {
			from += ma.getPlaceInstance().getNode().getName().getText()+":"+ma.getMarking()+", ";
		}
		return from;
	}
	
	public static String getMarkingAsString(Dictionary<Integer, Dictionary<String, Integer>> currentMarking, int[] verified, String with) {
		String to = "";
		for(int place = 0; place<currentMarking.size(); place++) {
			
			Enumeration<String> ekey2 = currentMarking.get(place).keys();
			
    		while(ekey2.hasMoreElements()) {
    			String color = ekey2.nextElement();
    			int number = currentMarking.get(place).get(color);
    			if(verified[place]!=2) {
    				if(color.contains("empty")) {
    					to += "empty, ";
    				}else {
    					to += number+"`\""+color+"\", ";		
    				}
				}else {
    				if(color.contains("empty")) {
    					to += "w`\""+color+"\", ";
    					if(StateSpaceGenerator.report) {
    						if(StateSpaceGenerator.unbounded_places.get(place)==null) {
    							String[] arr = new String[2];
    							arr[0] = with;
    							if(color.contains("empty")) arr[1]="()";
    							else arr[1]=color;
    							
    							StateSpaceGenerator.unbounded_places.put(place, arr);				
    						}
    					}
     					//to += "w`\"()\", ";
    	        		
    				}else {
        				to += "w`\""+color+"\", ";
        			}
    			}
    		}
    		
		}
		return to;
		
	}
	
	public static String findLinearComb(List<String> visitedStrings, List<Dictionary<Integer, Dictionary<String, Integer>>> visitedMarkings,
			Dictionary<Integer, Dictionary<String, Integer>> currentMarking, String from, String with, String to ) {
		
		for(Dictionary<Integer, Dictionary<String, Integer>> vm : visitedMarkings) {
				//If the place i has an equal marking than the place i in the current marking then
				//  verified[i] will be 1. If all indexes of verified are true that means that every place has an
				//  equal marking than that same place in the current marking. A 0 value will mean that they are different
				int[] verified = new int[currentMarking.size()];
				
				//Place indexes should be the same
				for(int i=0; i<currentMarking.size(); i++) {
					//if the color number is different break
					if(currentMarking.get(i).size()!=vm.get(i).size()) {
						break;
					}
					
					//Check if the keys are the same in every place
		    		Enumeration<String> ekey = currentMarking.get(i).keys();
		    		
		    		
		    		while(ekey.hasMoreElements()) {
			    		String current_color = ekey.nextElement();
			    		Enumeration<String> ekey2 = vm.get(i).keys();
			    		
			    		while(ekey2.hasMoreElements()) {
			    			String vm_color = ekey2.nextElement();
			    			//If the color is the same check	
			    			if (current_color.equals( vm_color ) ) {
			    				if(currentMarking.get(i).get(current_color) == vm.get(i).get(vm_color)) {
			    					//they are equal
			    					verified[i]=1;
			    				}
			    				else if(currentMarking.get(i).get(current_color) > vm.get(i).get(vm_color)) {
			    					//new marking is greater
			    					verified[i]=2;
			    				}
			    			}
			    		}
			    		
			    		for (String vs : visitedStrings ) {
			    			String[] vs_places = vs.split(", ");
			    			
			    			if(i<vs_places.length) {
			    				for ( String foos : vs_places[i].split(" ++")) {
			    					if(foos.contains(current_color)) {
			    						if(foos.split("`")[0].equals("w")) {
			    							verified[i]=2;
			    							break;
			    						}
			    					}
			    				}
			    			}
			    			
			    			
			    		}
			    		
			    	}
			
				}
				int sum=0;
				for(int v : verified) {
					//1 is equal, 2 is greater and 0 is lower
					if(v==0) {
						sum=0;
						break;
					}
					sum+=v;
				}
				if (sum > verified.length) {
					return getMarkingAsString(vm, verified, with);
					
				}
		}
		return "";
	}

	
	public static void replaceWithAncestors(String from, String ancestor_from) {
		Dictionary<String, String> foo = StateSpaceGenerator.reachability.get(from);
		StateSpaceGenerator.reachability.remove(from);
		if(foo!=null) {
			StateSpaceGenerator.reachability.put(ancestor_from, foo);
		
			Enumeration<String> ekey = StateSpaceGenerator.reachability.keys();
			while(ekey.hasMoreElements()) {
		    	String k = ekey.nextElement();
		    	Enumeration<String> ekey2 = StateSpaceGenerator.reachability.get(k).keys();
		    	while(ekey2.hasMoreElements()) {
		    		String k2 = ekey2.nextElement();
		    		String k3 = StateSpaceGenerator.reachability.get(k).get(k2);
		    		//Replace previous destinations with the updated ones
		    		if(k3.equals(from)) {
		    			StateSpaceGenerator.reachability.get(k).remove(k2);
		    			StateSpaceGenerator.reachability.get(k).put(k2, ancestor_from);
		    		}		
		    	}	
		    }	
		}
	}
	
	public static String replaceWEmpty(String ancestor_from) {
		String res = "";
		
		if(ancestor_from.contains("w`\"empty")) {
			for(int i = 0; i+8<ancestor_from.length(); i++) {
				if(ancestor_from.subSequence(i, i+9).equals("w`\"empty\"")  ) {
					res = ancestor_from.substring(0, i)+"w`\"()\""+ancestor_from.substring(i+9, ancestor_from.length());
				}
			}
		}
		return res;
	}
	
	public static String checkAncestors(String from, List<String> visitedStrings) {
		int max_w = 0, max_w_index=-1, j=0;
		
		for (String vs : visitedStrings ) {
			String[] vs_places = vs.split(", ");
			String[] from_places = from.split(", ");
			int[] verified = new int[vs_places.length];
			//for every place
			for(int i=0; i<vs_places.length; i++) {
				for ( String from_color : from_places[i].split(" ++")) {
					String[] from_color_split = from_color.split("`");
					for ( String color : vs_places[i].split(" ++")) {
						String[] color_split = color.split("`");
							
						if(color.contains("empty") && from_color.contains("empty")) {
							verified[i]=1;
							break;
						}
						else if(color_split.length < 2 || from_color_split.length <2 ) {
							continue;
						}
						else if( ('"'+from_color_split[1]+'"').equals(color_split[1]) || color_split[0].equals("w") || color.split("`")[0].equals(from_color.split("`")[0]) ) {
							verified[i]=1;
							break;
						
						}
					}
				}
			}
			int sum=0;
			for(int s: verified) sum+=s;
			if(sum>=vs_places.length) {
				int num_w = 0;
				for(int c=0; c+1<vs.length(); c++) {
					if(vs.charAt(c)=='w' && vs.charAt(c+1)=='`' ) {
						num_w++;
					}
				}
				if(num_w > max_w) {
					max_w=num_w;
					max_w_index=j;
				}
			}
			j++;
		}
		if(max_w>0) {
			return visitedStrings.get(max_w_index);
		}
		return "";
	}

	public static Dictionary<Integer, Dictionary<String, Integer>> getMarkingAsDict(State ss) {
		Dictionary<Integer, Dictionary<String, Integer>> places = new Hashtable<Integer, Dictionary<String, Integer>>();
		//For every place in the net...
		for(int i=0; i<ss.getAllMarkings().size(); i++) {
			
			Marking ma = ss.getAllMarkings().get(i);
			

			places.put(i, new Hashtable<String, Integer>());
			
			try {
				//Check for more than one token type in this place
				String[] msplit_colors = ma.getMarking().split("++");
				for(String ma2 : msplit_colors) {
					try{
						String[] msplit = ma2.split("`");
						places.get(i).put(msplit[1], Integer.parseInt(msplit[0]));
					}
					catch(Exception e){
						places.get(i).put("empty", 0);
					}
					
				}		
			}catch(Exception e) {
				//There is only one token type in this place or none token
				try{
					String[] msplit = ma.getMarking().split("`");
					places.get(i).put(msplit[1], Integer.parseInt(msplit[0]));
				}
				catch(Exception e2){
					//None token in this type
					places.get(i).put("empty", 0);
				}
				
			}
		}
		
		return places;
	}
	
	public static boolean findCoverAncestor(String previous_to) {
		//Check if the keys are the same in every place
		Enumeration<String> ekey = StateSpaceGenerator.reachability.keys();
		
		boolean cont = true;
		while(ekey.hasMoreElements() && cont) {
    		String from = ekey.nextElement();
    		Enumeration<String> ekey2 = StateSpaceGenerator.reachability.get(from).keys();
    		while(ekey2.hasMoreElements()) {
    			String with = ekey2.nextElement();
    			String to = StateSpaceGenerator.reachability.get(from).get(with);
    			//If the color is the same check	
    		}    		
    	}		
		return true;
	}
	
	public static HighLevelSimulator getSimulator() throws Exception, InterruptedException {
		final ProxyDaemon pd = ProxyDaemon.getDefaultInstance();
		//System.out.println("Waiting for CPN Tools");
		StateSpaceGenerator.ps = pd.getNext();

		//System.out.println("Waiting for syntax check");
		while (StateSpaceGenerator.ps.getPetriNet() == null) {
			Thread.sleep(500);
		}

		final HighLevelSimulator simulator = StateSpaceGenerator.ps.getSimulator();
		return simulator;
	}

	public static void handleEOL(final Date start, final int i) {
		StateSpaceGenerator.handleEOL(start, i, i, 0);
	}

	public static void handleEOL(final Date start, final int i, final int j) {
		StateSpaceGenerator.handleEOL(start, i, i, j);
	}

	public static void handleEOL(final Date start, final int i, final int j, final int k) {
		if (i % 10 == 0) {
			System.out.print('o');
		} else {
			System.out.print('.');
		}
		if (i % 50 == 0) {
			final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			final Date now = new Date();
			final long spent = now.getTime() - start.getTime();
			System.out.print(" - " + formatter.format(now) + " (" + spent / 86400000L + "d, "
			        + formatter.format(new Date(spent + 82800000L)) + ")\n");
			if (i > 0) {
				System.out.print(j + (k > 0 ? " (" + 100 * i / k + "%)" : "") + ":  ");
			}
		}
	}

	public synchronized static void serialize(final State s, final String binding,
	        final List<Instance<Place>> allRealPlaceInstances, final CompressedState c) {
		StateSpaceGenerator.toSerialize.add(c);
		if (!c.isCheckpoint()) {
			StateSpaceGenerator.toSerializeBindings.add(binding);
		}
	}

	static void profileEnd(final String name) {
		// final Date end = new Date();
		// Long value = StateSpaceGenerator.profile.get(name);
		// if (value == null) value = 0L;
		// value += end.getTime() - StateSpaceGenerator.start.getTime();
		// StateSpaceGenerator.profile.put(name, value);
	}

	static void profileStart() {
		// StateSpaceGenerator.start = new Date();
	}

	public static void CheckCheckpoints(HighLevelSimulator simulator, CompressedStateSet storage,
			BlockingDeque<CompressedState> waiting, List<Instance<Place>> allRealPlaceInstances) throws Exception {
		final State init = simulator.getMarking();
		try {  
			if (!StateSpaceGenerator.CHECKPOINTING) { throw new Exception("Skip check point"); }
			System.out.println("[pnpl] Trying to reestablish from checkpoint");
			new File("checkpoint.dmp").renameTo(new File("checkpoint.old"));
			final ObjectInputStream checkpoint = new ObjectInputStream(new BufferedInputStream(new FileInputStream(
			        new File("checkpoint.old"))));
			final ArrayList<String> values = (ArrayList<String>) checkpoint.readObject();
			
			if (values != null) {
				CompressedState.setValues(values);
				for (int i = checkpoint.readInt(); i > 0; i--) {
					waiting.add((CompressedState) checkpoint.readObject());
				}
				new File("states.dmp").renameTo(new File("states.old"));
				final DataInputStream states = new DataInputStream(new BufferedInputStream(new FileInputStream(
				        new File("states.old"))));
				final List<CompressedState> unchecked = new LinkedList<CompressedState>();
				try {
					while (true) {
						final CompressedState c = new CompressedState();
						c.readObject(states);
						if (c.isCheckpoint()) {
							storage.addAll(unchecked);
							unchecked.clear();
						} else {
							final String binding = states.readUTF();
							StateSpaceGenerator.serialize(null, binding, allRealPlaceInstances, c);
							unchecked.add(c);
						}
					}
				} catch (final EOFException e) {
					// We're done
				}
				states.close();
				StateSpaceGenerator.serialize(null, null, null, new CompressedState());
			}
			checkpoint.close();
		} catch (final Throwable t) {
			//System.out.println("Starting from scratch");
			storage.clear();
			waiting.clear();
			final CompressedState c = new CompressedState(allRealPlaceInstances, init);
			storage.add(c);
			waiting.add(c);
		}
	}
		
	public static void PrintReachability() {
		//System.out.println("==================================================");
		System.out.println("[pnpl] Printing reachability graph:");
		
		Enumeration<String> ekey = StateSpaceGenerator.reachability.keys();
	    while(ekey.hasMoreElements()) {
	    	String k = ekey.nextElement();
	    	System.out.printf("'%s' \n", k);
	    	
	    	Enumeration<String> ekey2 = StateSpaceGenerator.reachability.get(k).keys();
	    	
	    	while(ekey2.hasMoreElements()) {
	    		String t = ekey2.nextElement();
	    		String dest = StateSpaceGenerator.reachability.get(k).get(t);
		    	System.out.printf("[pnpl] \t%s -> %s \n", t, dest);
	    	}    
		     
	    }
	    //System.out.println("==================================================");		
	}
	
	public static void PrintDefaultOutput(CompressedStateSet storage, List<State> dead) throws FileNotFoundException, IOException {

		StateSpaceGenerator.serialize(null, null, null, new CompressedState());
		final ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(
		        new File("checkpoint.dmp"))));
		oos.writeObject(CompressedState.getValues());
		oos.writeInt(0);
		oos.close();
		System.out.println("[pnpl] Generated " + storage.size() + " states, " + StateSpaceGenerator.arcs + " arcs");
		StateSpaceGenerator.done.set(true);
		storage.printStats();
		
		System.out.println("[pnpl] Found " + dead.size() + " dead state" + (dead.size() == 1 ? "" : "s"));
		System.out.println("[pnpl] Printing dead states:");
		for (State s : dead) {
			System.out.println("[pnpl] \t"+s);
		}

	}
	
	public static void GenerateReport(List<Instance<Place>> allRealPlaceInstances) {
    	Enumeration<Integer> k = StateSpaceGenerator.unbounded_places.keys();
    	
    	while(k.hasMoreElements()) {
    		Integer i = k.nextElement();
		    String placename = allRealPlaceInstances.get(i).getNode().getName().asString();
		    String col = StateSpaceGenerator.unbounded_places.get(i)[1];
		    if(col.equals("()")) {
		    	System.out.printf("[pnpl] Place '%s' is unbounded with color '%s'\n", placename, "UNIT");
		    }
		    else {
		    	System.out.printf("[pnpl] Place '%s' is unbounded with color '%s'\n", placename, col);
		    }
    		
    	}
    	
    	if(StateSpaceGenerator.report) {
	    	boolean out = false;
    		for( Instance<Place> place : allRealPlaceInstances) {
	    		String placename = place.getNode().getName().asString();
	    		int placeid = StateSpaceGenerator.findId(placename);
	    		if(placename.contains("SAFETY")) {
	    			if(!out) {
	    				System.out.println("[pnpl] Safety report:");
				    	out = true;
	    			}
	    			String reach_marking = StateSpaceGenerator.findReachablePlace(placeid), reach = "";
	    			System.out.println(reach_marking);
	    			if(reach_marking.length()==0) reach = "empty";
	    			else{
	    				//String path = StateSpaceGenerator.getPathTo(reach_marking, StateSpaceGenerator.initialMarking);
	    				reach = reach_marking.split(", ")[placeid];
	    			}
	    			
	    			if(reach.contains("empty")) {
	    				System.out.printf("[pnpl] Place '%s' can not be reached\n", placename);
	    				System.out.println("[pnpl] Initial marking: "+ StateSpaceGenerator.initialMarking);
	    			}else {
	    				System.out.printf("[pnpl] Warning: place '%s' can be reached with marking '%s' !!\n", placename, reach);	
	    			}	    			
	    		}
	    	}
    	}

	}
	
	public static void GenerateGraphFile(String filename) throws IOException {
		System.out.println("[pnpl] Generating graph file...");
	    
	    File f = new File(filename);
	    System.out.println("[pnpl] Writing file in:\n\t"+ f.getAbsolutePath());
	    f.createNewFile();
	    
	    FileWriter myWriter = new FileWriter(filename);
	    myWriter.write("digraph A {\n");
	    
	    Enumeration<String> ekey = StateSpaceGenerator.reachability.keys();
	    while(ekey.hasMoreElements()) {
	    	String k = ekey.nextElement();
	    	Enumeration<String> ekey2 = StateSpaceGenerator.reachability.get(k).keys();
	    	
	    	while(ekey2.hasMoreElements()) {
	    		String t = ekey2.nextElement();
	    		String dest = StateSpaceGenerator.reachability.get(k).get(t);
	    		
	    		if(k.length()> 2 && dest.length()>2) {
	    			String from =  k.replace('"', '\'').trim().substring(0, k.length()-2);
	    			if(from.contains("w`'0'")) {
	    				String[] from_split = from.split("w`'0'");
	    				from = from_split[0]+"w`'()'"+from_split[1];
	    			}
	    			
	    			String to = dest.replace('"', '\'').trim().substring(0, dest.length()-2);
	    			if(to.contains("w`'0'")) {
	    				String[] from_split = to.split("w`'0'");
	    				to = from_split[0]+"w`'()'"+from_split[1];
	    			}
	    			
	    			myWriter.write("\t\"" + from +
			    			"\" -> \"" + to + "\" "
			    					+ "[label=\""+t+"\"];\n");
	    		}		
	    	}
	   }
	    myWriter.write("}");
	    myWriter.close();
	}	
}
