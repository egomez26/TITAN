package org.pnpl.export.handlers;

import org.eclipse.core.commands.IHandler;

public class ExportHandlerPNPL extends ExportHandler implements IHandler {

	@Override
	protected String getExtensionPointIdentifier() {
		return "org.pnpl.export.pnpl";
	}

}
