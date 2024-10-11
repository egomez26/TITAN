package org.pnpl.analysis.handlers;

public class AnalysisHandlerPNPL extends AnalysisHandler {

	@Override
	protected String getExtensionPointIdentifier() {
		return "org.pnpl.analysis.analyzer.pnpl";
	}
}
