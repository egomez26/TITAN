package org.pnpl.analysis.invariant.test;

import org.jacop.core.*;
import org.jacop.fz.FlatzincLoader;
import org.jacop.search.*;
import org.jacop.constraints.*;


public class Main {

	public static void main(String[] args) {
		//loadFlatZincFile("D://tools//Minizinc//example3_pinv_en_lugares.fzn");
		createConstraints();		
	}
	
	private static void createConstraints() {
		System.out.println("Hola");
		Store store = new Store();
		
		int size = 7;
		
		IntVar[] vars = new IntVar[size];
				
		// define finite domain variables
		for(int i=0; i<2; i++)
			vars[i] = new IntVar(store, "f"+i, 0, 1);
		
		int j = 0;
		for(int i=2; i<5; i++) {
			vars[i] = new IntVar(store, "y"+j, 0, 10);
			j++;
		}
		
		j=0;
		for(int i=5; i<size; i++) {
			vars[i] = new IntVar(store, "x"+j, 0, 10);
			j++;
		}
		
		//store.impose(new XplusYgtC(vars[0], vars[1], 0));
		IntVar sumZero = new IntVar(store, "sumZero", 0, 0);
				
		// f1, f2
		IntVar[] f = {vars[0], vars[1]};
		// y1, y0, y2
		IntVar[] y = {vars[3], vars[2], vars[4]};
		
		// f1 + f2 > 0
		store.impose(new SumInt(f, ">", sumZero));
		// y0 + y1 + y2 > 0
		//store.impose(new XplusYplusQgtC(vars[2], vars[3], vars[4], 0));	
		
		// vars[5] = x1 = y1 * f2
		// vars[6] = x2 = y2 * f1
		IntVar[] temp = {vars[5], vars[2], vars[6]}; // x1, y0, x2
		store.impose(new LinearInt(temp, new int[] {1, -1,  1}, "==", 0));
		store.impose(new LinearInt(y, new int[] { -1, 1, -1}, "==", 0));
		store.impose(new LinearInt(y, new int[] { 1,  1, -1}, "==", 0));
	
		store.impose(new XmulYeqZ(vars[0],vars[4],vars[6])); // f1 * y2 = x2
		store.impose(new XmulYeqZ(vars[1],vars[3],vars[5])); // f2 * y1 = x1
				
		printConstraints(store);
		
		Search<IntVar> search = new DepthFirstSearch<IntVar>();
		//SelectChoicePoint<IntVar> select = new InputOrderSelect<IntVar>(store, vars,new IndomainMin<IntVar>());
		SelectChoicePoint<IntVar> select = new SimpleSelect<IntVar>(vars, new SmallestDomain<IntVar>(), new IndomainMin<IntVar>());

		search.getSolutionListener().searchAll(true);
		search.getSolutionListener().recordSolutions(true);
		
		boolean result = search.labeling(store, select);
		if ( result )
	  	    System.out.println("*** Yes");
		else 
		    System.out.println("*** No");
		search.getSolutionListener().printAllSolutions();
	}
	
	@SuppressWarnings("unused")
	private static void loadFlatZincFile(String fullname) {
		String[] arg = new String[2];
		arg[0] = "-s";
		arg[1] = fullname;
		
		long T1, T2, T;
		T1 = System.currentTimeMillis();
		
		FlatzincLoader flat = new FlatzincLoader(arg);
		
		flat.load();
		Store store = flat .getStore();
				
		System.out.println (store);
		System.out.println("============================================");
		System.out.println(flat.getTables());
		System.out.println("============================================");

		System.out.println( "\nIntVar store size: "+ store.size()+
				    "\nNumber of constraints: " + store.numberConstraints()
				    );
		printConstraints(store);
		
		DepthFirstSearch<Var> label = flat.getDFS();
		SelectChoicePoint<Var> select = flat.getSelectChoicePoint();
		
		label.getSolutionListener().searchAll(true);
		label.getSolutionListener().recordSolutions(true);
		
		Var cost = flat.getCost();
		boolean result = false;
		if (cost != null)
		    result = label.labeling(flat.getStore(), select, cost);
		else
		    result = label.labeling(flat.getStore(), select);

		flat.getSolve().statistics(result);
		//System.out.println(flat.getTables());
		//System.out.println(flat.getSearch());
		System.out.println("cost: " + flat.getCost());

		if ( result )
	  	    System.out.println("*** Yes");
		else 
		    System.out.println("*** No");

		T2 = System.currentTimeMillis();
		T = T2 - T1;
		System.out.println("\n\t*** Execution time = "+ T + " ms");
		label.getSolutionListener().printAllSolutions();
	}
	
	private static void printConstraints(Store store) {
		System.out.println( "***************** Constraints: ");// + store.getConstraints().toString());
		for(Constraint c : store.getConstraints()) {
			System.out.println("*" + c.toString());
		}
	}
}