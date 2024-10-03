package org.pnpl.analysis.helpers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

import PetriNets.*;
import variability.BinaryExpression;
import variability.BinaryOperator;
import variability.Expression;
import variability.VariabilityFactory;


public class PetriNetsHelper {
	public List<Place> getInputPlaces(Transition t) {
		if (t == null) return null;

		List<Place> list = Lists.newArrayList();
		List<PTArc> inputs = t.getInputs();

		if ((inputs != null) && (!inputs.isEmpty())) {
			for (PTArc a : inputs) {
				if(a.getInput() != null)
					list.add(a.getInput());									
			}				
		}

		Collections.sort(list, (p1, p2) -> {
			return p1.getName().compareTo(p2.getName());
		});
		return list;
	}
	
	public List<Transition> getOutputTransitions(Place p) {
		if (p == null) return null;

		List<Transition> list = Lists.newArrayList();
		List<PTArc> outputs = p.getOutputs();

		if ((outputs != null) && (!outputs.isEmpty())) {
			for (PTArc a : outputs) {
				if(a.getOutput() != null)
					list.add(a.getOutput());
			}							
		}

		Collections.sort(list, (t1, t2) -> {
			return t1.getName().compareTo(t2.getName());
		});
		return list;
	}

	public Table<Place, Transition, Pair<Arc,Integer>> getIncidenceTableWithArcs(PetriNet pn) {
		Table<Place, Transition, Pair<Arc,Integer>> table = HashBasedTable.create();

		for (Arc a : pn.getArcs()) {
			Place p = null;
			Transition t  = null;
			Integer weight  = 0;
			
			if(a instanceof PTArc) {
				p = ((PTArc) a).getInput();
				t = ((PTArc) a).getOutput();
				weight = -1;
			} else if (a instanceof TPArc) {
				p = ((TPArc) a).getOutput();
				t = ((TPArc) a).getInput();
				weight = 1;
			}
			
			if (p!=null && t!=null) {
				if (table.contains(p, t))			
					weight += table.get(p, t).getTwo();
				table.put(p, t, Tuples.pair(a, weight));
			}
		}
		return table;
	}

	
	public Table<Place, Transition, Pair<Expression,Integer>> getIncidenceTableWithPCs(PetriNet pn, Map<String, Expression> pcExpression) {
		Table<Place, Transition, Pair<Expression,Integer>> table = HashBasedTable.create();

		for (Arc a : pn.getArcs()) {
			Place p = null;
			Transition t  = null;
			Integer weight  = 0;
			
			if(a instanceof PTArc) {
				p = ((PTArc) a).getInput();
				t = ((PTArc) a).getOutput();
				weight = -1;				
			} else if (a instanceof TPArc) {
				p = ((TPArc) a).getOutput();
				t = ((TPArc) a).getInput();
				weight = 1;
			}
			
			if (p!=null && t!=null) {
				Expression expr = pcExpression.get(a.getName());
				
				if (table.contains(p, t)) {
					weight += table.get(p, t).getTwo();		
					
					if (expr != null) {
						BinaryExpression bin =  VariabilityFactory.eINSTANCE.createBinaryExpression();
						bin.setOperator(BinaryOperator.OR);
						bin.setRight(table.get(p, t).getOne());
						bin.setLeft(expr);
						expr = (Expression) bin;
					}
					else
						expr = table.get(p, t).getOne();
				}				
				
				table.put(p, t, Tuples.pair(expr, weight));
			}
		}
		return table;
	}
	
	public Table<Place, Transition, Integer> getIncidenceTable(PetriNet pn) {
		Table<Place, Transition, Integer> table = HashBasedTable.create();

		for (Arc a : pn.getArcs()) {
			Place p = null;
			Transition t  = null;
			Integer weight  = 0;
			
			if(a instanceof PTArc) {
				p = ((PTArc) a).getInput();
				t = ((PTArc) a).getOutput();
				weight = -1;				
			} else if (a instanceof TPArc) {
				p = ((TPArc) a).getOutput();
				t = ((TPArc) a).getInput();
				weight = 1;
			}
			
			if (p!=null && t!=null) {
				if (table.contains(p, t))
					weight += table.get(p, t);	
					
				table.put(p, t, weight);
			}
		}
		return table;
	}

	public int[][] getIncidenceMatrix(PetriNet pn) {
		if (pn == null ) return null;

		int[][] matrix = new int[pn.getPlaces().size()][pn.getTrans().size()];
		Table<Place, Transition, Integer> table = getIncidenceTable(pn);

		int row = 0;
		int col = 0;		
		for (Place p : pn.getPlaces()) {
			for(Transition t : pn.getTrans()) {
				if (table.contains(p, t))
					matrix[row][col] = table.get(p, t);
				col++;
			}
			row++;
			col = 0;
		}
		//printMatrix(matrix);
		return matrix;		
	}

	@SuppressWarnings("unused")
	private void printMatrix (int[][] matrix) {
		int row = 0;
		int col = 0;

		String line = "";
		for (row=0; row < matrix.length; row++) {
			for (col=0; col < matrix[0].length; col++) {
				line = line + " " + matrix[row][col];				
			}
			System.out.println(line);
			line = "";
		}
	}
}
