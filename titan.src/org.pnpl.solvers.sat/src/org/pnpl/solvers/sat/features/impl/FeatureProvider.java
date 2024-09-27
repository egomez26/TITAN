package org.pnpl.solvers.sat.features.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.pnpl.solvers.sat.features.IFeature;
import org.pnpl.solvers.sat.features.IFeatureProvider;

public class FeatureProvider implements IFeatureProvider{
	
	private LinkedHashMap<IFeature, Boolean> configuration = new LinkedHashMap<>();
	
	public FeatureProvider(String... feats) {
		for (String f : feats) 
			this.configuration.put(new Feature(f), false);		
	}
	
	public boolean setValue(String f, boolean v) {
		IFeature feat = this.getFeature(f);
		if (feat==null) return false;
		return this.configuration.put(feat, v);		
	}

	@Override
	public boolean isValidFeature(String name) {
		return this.getFeature(name)!=null;
	}

	@Override
	public IFeature getFeature(String name) {
		for (IFeature f : this.configuration.keySet()) 
			if (f.getName().equals(name)) return f;		
		return null;
	}

	@Override
	public boolean getFeatureValue(String name) {
		return this.configuration.get(this.getFeature(name));
	}

	public List<IFeature> getFeatures() {
		return new ArrayList<IFeature>(this.configuration.keySet());
	}

}
