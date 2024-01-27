package com.mobiera.lib.etsi102226.api.model;

import com.mobiera.lib.etsi102226.api.model.tlv.SystemSpecificParameters;

/**
 * Base class for Install Parameters field according to GlobalPlatform 2.1.1
 * 
 * Each applet type shall extend this class
 * 
 * @author genaris
 *
 */
public class BasicInstallParameters extends InstallParameters {

	@Override
	protected SystemSpecificParameters getSystemSpecificParameters() {
		SystemSpecificParameters sp = new SystemSpecificParameters();
		sp.setNonVolatileDataSpaceLimit(0);
		sp.setVolatileDataSpaceLimit(0);
		return sp;
	}
	
}
