package org.pnpl.model.variability.editor

import org.pnpl.model.variability.editor.PNPL_variabilityStandaloneSetupGenerated

class PNPL_variabilityStandaloneSetup extends PNPL_variabilityStandaloneSetupGenerated {
	def static void doSetup() {
		new PNPL_variabilityStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}