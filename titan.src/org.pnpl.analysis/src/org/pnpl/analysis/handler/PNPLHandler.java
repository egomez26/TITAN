package org.pnpl.analysis.handler;

public class PNPLHandler extends AbstractAnalysisHandler {

	@Override
	protected String getExtensionPointIdentifier() {
		return "org.pnpl.analysis.analyzer.lines";
	}

}
