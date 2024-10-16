package org.pnpl.export.handlers;

import org.eclipse.core.commands.IHandler;

public class ExportHandlerProducts extends ExportHandler implements IHandler {

	@Override
	protected String getExtensionPointIdentifier() {
		return "org.pnpl.export.products";
	}

}
