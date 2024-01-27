package com.mobiera.lib.etsi102226.api.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mobiera.lib.etsi102226.api.model.tlv.ApplicationSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.SystemSpecificParameters;

/**
 * Base class for Install Parameters field according to GlobalPlatform 2.1.1
 * 
 * Each applet type shall extend this class
 * 
 * @author genaris
 *
 */
public class InstallParameters {

	protected ApplicationSpecificParameters appParameters;
	
	/**
	 * Base implementation: no system-specific parameters. Subclasses might
	 * override this.
	 * @return
	 */
	protected SystemSpecificParameters getSystemSpecificParameters() {
		return null;
	}
	
	public InstallParameters() {
		this.appParameters = new ApplicationSpecificParameters();
	}
	
	public void setAppParameters(ApplicationSpecificParameters appParameters) {
		this.appParameters = appParameters;
	}
	
	public ApplicationSpecificParameters getAppParameters() {
		return this.appParameters;
	}
	
	public byte [] getBytes() throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		if (getSystemSpecificParameters() != null)
			bo.write(getSystemSpecificParameters().getBytes());
		
		if (appParameters != null)
			bo.write(appParameters.getBytes());
		
		return bo.toByteArray();
	}
	
}
