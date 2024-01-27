package com.mobiera.lib.etsi102226.api.model;

import com.mobiera.lib.etsi102226.api.model.tlv.ApplicationSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.STKAppletSystemSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.SystemSpecificParameters;

/**
 * Base class for Install Parameters field according to ETSI 102.226
 * 
 * Each applet type shall extend this class
 * 
 * @author genaris
 *
 */
public class STKAppletInstallParameters extends InstallParameters {

	protected STKAppletSystemSpecificParameters sysParameters;
	
	public STKAppletInstallParameters() {
		super();
	}
	
	public STKAppletInstallParameters(ApplicationSpecificParameters appParams, 
			STKAppletSystemSpecificParameters sysParams) {
		super();
		setAppParameters(appParams);
		setSystemParameters(sysParams);
		
	}
	
	public void setSystemParameters(STKAppletSystemSpecificParameters sysParams) {
		this.sysParameters = sysParams;
	}
	
	@Override
	protected SystemSpecificParameters getSystemSpecificParameters() {
		return this.sysParameters;
	}
	
	public static class Builder {
		protected STKAppletSystemSpecificParameters sysParameters;
		protected ApplicationSpecificParameters appParameters;
		
		public Builder() {
			sysParameters = new STKAppletSystemSpecificParameters();
		}
		
		public Builder sysParameters(STKAppletSystemSpecificParameters sysParameters) {
			this.sysParameters = sysParameters;
			return this;
		}
		
		public Builder appParameters(ApplicationSpecificParameters appParameters) {
			this.appParameters = appParameters;
			return this;
		}
		
		public STKAppletInstallParameters build() {
			STKAppletInstallParameters output = new STKAppletInstallParameters();
			output.appParameters = this.appParameters;
			output.sysParameters = this.sysParameters;
			
			return output;
		}
	}
	
	
}
