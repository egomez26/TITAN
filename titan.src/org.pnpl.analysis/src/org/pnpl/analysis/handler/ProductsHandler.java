package org.pnpl.analysis.handler;

public class ProductsHandler extends AbstractAnalysisHandler {

	@Override
	protected String getExtensionPointIdentifier() {
		return "org.pnpl.analysis.analyzer.products";
	}

}