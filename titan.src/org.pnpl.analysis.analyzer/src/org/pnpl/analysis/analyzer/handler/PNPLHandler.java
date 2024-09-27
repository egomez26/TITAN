package org.pnpl.analysis.analyzer.handler;

public class PNPLHandler extends AbstractAnalysisHandler {

	@Override
	protected String getExtensionPointIdentifier() {
		return "org.pnpl.analysis.analyzer.lines";
	}

}
