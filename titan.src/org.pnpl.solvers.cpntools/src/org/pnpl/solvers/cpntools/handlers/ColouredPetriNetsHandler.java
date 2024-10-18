package org.pnpl.solvers.cpntools.handlers;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.cpntools.accesscpn.engine.highlevel.HighLevelSimulator;
import org.cpntools.accesscpn.engine.highlevel.checker.Checker;
//import org.cpntools.accesscpn.engine.highlevel.checker.ErrorInitializingSMLInterface;
import org.cpntools.accesscpn.engine.highlevel.instance.Instance;
import org.cpntools.accesscpn.engine.highlevel.instance.Marking;
import org.cpntools.accesscpn.engine.highlevel.instance.State;
import org.cpntools.accesscpn.model.PetriNet;
import org.cpntools.accesscpn.model.Place;
import org.cpntools.accesscpn.model.Transition;
import org.cpntools.accesscpn.model.importer.DOMParser;

import org.pnpl.solvers.cpntools.generators.StateSpaceGenerator;
import org.pnpl.solvers.cpntools.generators.StateSpaceGenerator.ComputeThread;
import org.pnpl.solvers.cpntools.generators.CollectionsCompressedStateSet;
import org.pnpl.solvers.cpntools.generators.CompressedState;
import org.pnpl.solvers.cpntools.generators.CompressedStateSet;

class Stream {
    String color;
    Vector<Event> data;
    Boolean editable;
    String name;
    String style; /* "signal" | "dots" | "graph" | "slim graph" | "plot" | "slim plot" | "events" | "unit events" | "bubbles" */
    Object text; /* ((e: TesslaEvent) => string) | null; */
}

public class ColouredPetriNetsHandler {
	public String prefixPlace;

	public void stateExploration(final File file) {		
		//StateSpaceGenerator.THREADS = Runtime.getRuntime().availableProcessors();
		StateSpaceGenerator.THREADS = 1;
		StateSpaceGenerator.threadCount = new AtomicInteger(StateSpaceGenerator.THREADS);
		System.out.println("[pnpl] Using " + StateSpaceGenerator.THREADS + " thread" 
				+ (StateSpaceGenerator.THREADS == 1 ? "" : "s"));

		try {
			final HighLevelSimulator simulator = load(file);

			//A petri net must be provided by this point
			System.out.println("[pnpl] Retrieving all real place instances...");
			final List<Instance<Place>> allRealPlaceInstances = simulator.getAllRealPlaceInstances();

			System.out.println("[pnpl] Printing places info:");
			for( Instance<Place> ip : allRealPlaceInstances) {
				System.out.println("[pnpl] \t" + ip.getNode().getName() + "\tmarking: " + ip.getNode().getInitialMarking().asString());
			}

			System.out.println("[pnpl] Compressed state created");
			CompressedStateSet storage = new CollectionsCompressedStateSet();

			final BlockingDeque<CompressedState> waiting = new LinkedBlockingDeque<CompressedState>();
			System.out.println("[pnpl] Blocking queue created");

			//Check if we can restore a checkpoint
			StateSpaceGenerator.CheckCheckpoints(simulator, storage, waiting, allRealPlaceInstances);;

			//Start threads
			final List<State> dead = Collections.synchronizedList(new LinkedList<State>());

			int threads = 1;
			final List<Thread> threadPool = new ArrayList<Thread>();
			final Date start = new Date();

			//State space exploration is made by this threads
			threadPool.add(new ComputeThread(waiting, storage, allRealPlaceInstances, simulator, dead, start));
			while (threads < StateSpaceGenerator.THREADS) {
				System.out.println("[pnpl] Started thread " + threads + " of " + StateSpaceGenerator.THREADS);
				threadPool.add(new ComputeThread(waiting, storage, allRealPlaceInstances, StateSpaceGenerator.ps
						.getSimulatorClone(), dead, start));
				threads++;
			}
			System.out.println("[pnpl] Started thread " + threads + " of " + StateSpaceGenerator.THREADS);

			final DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(
					new File("states.dmp"))));
			new Thread() {
				@Override
				public void run() {
					try {
						while (true) {
							CompressedState s;
							try {
								s = StateSpaceGenerator.toSerialize.poll(1, TimeUnit.SECONDS);
								if (s == null) {
									if (StateSpaceGenerator.done.get()) {
										break;
									}
								} else {
									s.writeObject(outputStream);
									if (s.isCheckpoint()) {
										outputStream.flush();
									} else {
										saveBindings(outputStream, StateSpaceGenerator.toSerializeBindings.take());
									}
								}
							} catch (final InterruptedException e) {
								// Ignore
							}
						}
						outputStream.flush();
						outputStream.close();
					} catch (final IOException e2) {
						e2.printStackTrace();
					}
				}

				public void saveBindings(final DataOutputStream o, final String bindings) throws IOException {
					{
						o.writeUTF(bindings);
					}
				}
			}.start();

			System.out.print("Generating\n0:  0  0  0  .\t");
			start.setTime(new Date().getTime());
			for (Thread t : threadPool) {
				t.start();
			}
			for (Thread t : threadPool) {
				t.join();
			}
			StateSpaceGenerator.handleEOL(start, 0);

			//Print acces-CPN default output
			StateSpaceGenerator.PrintDefaultOutput(storage, dead);
			StateSpaceGenerator.PrintReachability();

			if(StateSpaceGenerator.report) {
				StateSpaceGenerator.GenerateReport(allRealPlaceInstances);
			}

			String dotFile = file.getName().substring(0, file.getName().lastIndexOf('.')) + ".dot";
			StateSpaceGenerator.GenerateGraphFile(file.getParent() + "/" + dotFile);

			System.out.println("[pnpl] " + StateSpaceGenerator.profile);
			System.out.println("[pnpl] Reachibility Graph ended");

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	
	private int getTokensBefore(int currentTime, Marking marking) {
		int tokenTime, numTokens = 0;
		if (marking.getTokenCount() > 0 && marking.getMarking().split("@").length > 1) {
			if(marking.getMarking().indexOf("+++") > 0)	{
				// Marking: 1`1@1260 +++ 1`3@534					
				String aux = marking.getMarking().replaceAll("\\n|\\t|\\s","");
				aux = aux.replace("+++", " ");
				String[] tokenString = aux.split(" ");

				for(String token : tokenString) {					
					String[] tokenInfo = token.split("@");
					String[] ValueAndType = tokenInfo[0].split("`");
					tokenTime = Integer.valueOf(tokenInfo[1]);
					if (currentTime >= tokenTime) numTokens += Integer.valueOf(ValueAndType[0]);
				}
			} 
			else {
				// Marking: 1`1@1260
				String[] tokenInfo = marking.getMarking().split("@");
				String[] ValueAndType = tokenInfo[0].split("`");
				tokenTime = Integer.valueOf(tokenInfo[1]);
				if (currentTime >= tokenTime) numTokens += Integer.valueOf(ValueAndType[0]);
			}
		}
		return numTokens;
	}
	
	private void populateWithPlaces(int step, int currentTime, List<Marking> allMarkings, Hashtable<String, Vector<Event>> inputStreams) {
		System.out.println("[pnpl] allMarkings " + allMarkings.toString());
		
		for (Marking marking : allMarkings) {
			String stream = marking.getPlaceInstance().getNode().getName().getText();
			if (stream.indexOf(prefixPlace) >= 0)
				stream = stream.substring(stream.indexOf(prefixPlace), prefixPlace.length() - 1);
			
			Vector<Event> t_stream = inputStreams.get(stream);
			Event lastTe;
			if (t_stream == null) {
				t_stream = new Vector<Event>();
				lastTe = new Event();
			} else {
				lastTe = t_stream.lastElement();
			}

			if (currentTime > lastTe.getTime()) {
				int numTokens = getTokensBefore(currentTime, marking);
				Event te = new Event();
				te.setStream(stream);
				te.setStep(step);
				te.setTime(currentTime);
				//te.setType(ValueAndType[1]);
				te.setType("Int");
				te.setValue(numTokens);
	
				/* Consider TinyExample:
				 * Simulation step: 1, time: 0
				 * New markings: [Worker1: 1`1@0, Worker2: 1`1@0, Worker3: 1`1@0, Todo: 1`1@50, Finished: 9`1@0]
				 * ...
				 * Simulation step: 10, time: 0
				 * New markings: [Worker1: 1`1@0, Worker2: 1`1@0, Worker3: 1`1@0, Todo: 10`1@50, Finished: empty]
				 * Simulation step: 11, time: 50
				 * New markings: [Worker1: 1`1@0, Worker2: 1`1@0, Worker3: 1`1@0, Todo: 9`1@50, Finished: 1`1@53]
				 * */
	
				// Prevent duplicated events and force monotonically increasing of time
				//if ((te.getTime() > lastTe.getTime()) && !t_stream.contains(te)) {
				t_stream.add(te);
				inputStreams.put(stream, t_stream);
				System.out.println("[pnpl] event: " + te.toString());
				System.out.println("[pnpl] last event: " + lastTe.toString());
			}
		}
	}
	
	
	private void populateWithTransitions(int step, int currentTime, Instance<? extends Transition> execTransition, Hashtable<String, Vector<Event>> inputStreams) {
		if (execTransition != null) {
			System.out.println("[pnpl] Executed transition " + execTransition.toString());
		
			String stream = execTransition.getNode().getName().getText();
			if (stream.indexOf(prefixPlace) >= 0)
				stream = stream.substring(stream.indexOf(prefixPlace), prefixPlace.length() - 1);
			
			Vector<Event> t_stream = inputStreams.get(stream);
			Event lastTe;
			if (t_stream == null) {
				t_stream = new Vector<Event>();
				lastTe = new Event();
				lastTe.setStep(step);
			} else {
				lastTe = t_stream.lastElement();
			}
			
			Integer val = 1;
			if (lastTe.getTime() == currentTime) {
				/* Transition is fired several times in a row */
				val = (Integer) lastTe.getValue();
				lastTe.setValue(val + 1);
			} else {
				/* Fresh event  */
				Event te = new Event();
				te.setStream(stream);
				te.setStep(step);
				te.setTime(currentTime);
				te.setType("Int");
				te.setValue(val);
				t_stream.add(te);
			}
			inputStreams.put(stream, t_stream);
			lastTe = t_stream.lastElement();
			System.out.println("[pnpl] event: " + lastTe.toString() + " in " + t_stream.toString());
		}
	}

	public Hashtable<String, Vector<Event>> simulate(File file, final int amount) throws Exception {	
		System.out.println("[pnpl] Simulation launching");
        Hashtable<String, Vector<Event>> placeStreams = new Hashtable<String, Vector<Event>>();
        Hashtable<String, Vector<Event>> transitionStreams = new Hashtable<String, Vector<Event>>();

		StateSpaceGenerator.THREADS = Runtime.getRuntime().availableProcessors();
		StateSpaceGenerator.threadCount = new AtomicInteger(StateSpaceGenerator.THREADS);
		System.out.println("[pnpl] Using " + StateSpaceGenerator.THREADS + " thread" 
				+ (StateSpaceGenerator.THREADS == 1 ? "" : "s"));

		try {
			final HighLevelSimulator simulator = load(file);
			int step = 0, prevTime = -1, currentTime = -1;
			for (int i = 0; i < amount; i++) {
				System.out.println("[pnpl] Simulation step: " + step + ", time: " + currentTime);
				List<Marking> allMarkings = simulator.getMarking().getAllMarkings();
//				List<Instance<Transition>> trans = simulator.getAllTransitionInstances();
//				List<Instance<? extends Transition>> enabled = simulator.isEnabled(trans);
//				System.out.println("[pnpl] Enabled: " + enabled.toString());
				Instance<? extends Transition> execTransition = simulator.execute();
				prevTime = currentTime;
				currentTime = Integer.valueOf(simulator.getTime());
				step = simulator.getStep().intValue();
				if (currentTime > prevTime) {
					/* Only populate placeStreams when clock changes */
					populateWithPlaces(step, currentTime, allMarkings, placeStreams);
				}
				populateWithTransitions(step, currentTime, execTransition, transitionStreams);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		System.out.println("[pnpl] Simulation ended");
		
		/* placeStreams and transitionStreams */
		Hashtable<String, Vector<Event>> placeAndTransitionStreams = new Hashtable<String, Vector<Event>>();
		placeAndTransitionStreams.putAll(placeStreams);
		placeAndTransitionStreams.putAll(transitionStreams);
		return placeAndTransitionStreams;
	}	

	private HighLevelSimulator load(final File file) throws Exception {

		System.out.println("[pnpl] Getting petri net from file...");
		final PetriNet petriNet = DOMParser.parse(file.toURI().toURL());

		System.out.println("[pnpl] Creating simulator instance...");

		//Simulator instance
		final HighLevelSimulator simulator = HighLevelSimulator.getHighLevelSimulator();

		simulator.setSimulationReportOptions(false, false, "");

		final Checker checker = new Checker(petriNet, file, simulator);

		try {
			checker.checkEntireModel(file.getParent(), file.getParent());
		//} catch (final ErrorInitializingSMLInterface e) {
		} catch (Exception e) {
			// Ignore
		}
		System.out.println("[pnpl] Simulator created");
		return simulator;
	}
}