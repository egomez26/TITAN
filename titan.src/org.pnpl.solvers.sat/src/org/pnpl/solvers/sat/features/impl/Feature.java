package org.pnpl.solvers.sat.features.impl;

import org.pnpl.solvers.sat.features.IFeature;

public class Feature implements IFeature {
	private String name;

	public Feature(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o==null) return false;
		if (!(o instanceof Feature)) return false;
		return ((Feature)o).name.equals(this.name);
	}

}
