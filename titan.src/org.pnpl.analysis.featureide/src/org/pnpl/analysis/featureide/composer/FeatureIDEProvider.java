package org.pnpl.analysis.featureide.composer;

import de.ovgu.featureide.fm.core.configuration.Configuration;
import de.ovgu.featureide.fm.core.configuration.SelectableFeature;

import org.pnpl.solvers.sat.features.IFeature;
import org.pnpl.solvers.sat.features.IFeatureProvider;
import org.pnpl.solvers.sat.features.impl.Feature;

public class FeatureIDEProvider implements IFeatureProvider {
	
	private Configuration cfg;
	
	public FeatureIDEProvider (Configuration cfg) {
		this.cfg = cfg;
	}

	@Override
	public boolean isValidFeature(String name) {
		for (SelectableFeature sf : this.cfg.getFeatures() ) 
			if (sf.getName().equals(name)) return true;
		
		return false;
	}

	@Override
	public IFeature getFeature(String token) {
		if (isValidFeature(token)) return new Feature(token);
		return null;
	}

	@Override
	public boolean getFeatureValue(String name) {
		return this.cfg.getSelectedFeatureNames().contains(name);
	}
}
