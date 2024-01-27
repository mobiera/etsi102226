package com.mobiera.lib.etsi102226.api.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.mobiera.lib.etsi102226.api.model.tlv.ApplicationSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.SIMToolkitApplicationSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.STKAppletSystemSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.SystemSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.UICCAccessApplicationSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.UICCToolkitApplicationSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.STKAppletSystemSpecificParameters.Builder;

/**
 * Base class for Install Parameters field according to ETSI 102.226
 * 
 * Each applet type shall extend this class
 * 
 * @author genaris
 *
 */
public class UICCAppletInstallParameters extends InstallParameters {

	protected UICCToolkitApplicationSpecificParameters toolkitParameters;
	protected UICCAccessApplicationSpecificParameters accessParameters;
	protected SystemSpecificParameters sysParameters;
	
	public UICCAppletInstallParameters() {
		super();
	}
	
	public UICCAppletInstallParameters(ApplicationSpecificParameters appParams, 
			UICCToolkitApplicationSpecificParameters uiccParams) {
		super();
		setAppParameters(appParams);
		setUICCToolkitParameters(uiccParams);
	}
	
	public void setUICCToolkitParameters(UICCToolkitApplicationSpecificParameters uiccParams) {
		this.toolkitParameters = uiccParams;
	}

	public void setUICCAccessParameters(UICCAccessApplicationSpecificParameters accessParams) {
		this.accessParameters = accessParams;
	}

	@Override
	protected SystemSpecificParameters getSystemSpecificParameters() {
		return this.sysParameters;
	}
	
	@Override
	public byte [] getBytes() throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		
		// Add UICC Specific parameters
		bo.write(toolkitParameters.getBytes());
		
		bo.write(accessParameters.getBytes());
				
		// Append System / App specific parameters (if any)
		bo.write(super.getBytes());
		
		return bo.toByteArray();
	}
	
	/**
	 * Builder for UICCAppletInstallParameters. Handy for most UICC Toolkit applets.
	 * 
	 * @author Ariel Gentile
	 *
	 */
	public static class Builder {
		
		private int nonVolatileDataSpaceLimit = 0;
		private int volatileDataSpaceLimit = 0;
		protected UICCToolkitApplicationSpecificParameters toolkitParameters;
		protected UICCAccessApplicationSpecificParameters accessParameters;
		protected SystemSpecificParameters sysParameters;
		protected ApplicationSpecificParameters appParameters;
		
		
		public Builder() {
			toolkitParameters = new UICCToolkitApplicationSpecificParameters();

		}
		
		public Builder appParameters(ApplicationSpecificParameters params) {
			this.appParameters = params;
			return this;
		}
		
		public Builder nonVolatileDataSpaceLimit(int limit) {
			this.nonVolatileDataSpaceLimit = limit;
			return this;
		}
		
		public Builder volatileDataSpaceLimit(int limit) {
			this.nonVolatileDataSpaceLimit = limit;
			return this;
		}
		
		public Builder uiccAccessDomain(byte [] accessDomain) {
			if (accessParameters == null)
				accessParameters = new UICCAccessApplicationSpecificParameters();
			this.accessParameters.setUICCFileSystemAccessDomain(accessDomain);
			return this;
		}
		
		public Builder addAdfAccessDomain(
				UICCAccessApplicationSpecificParameters.AccessDomainEntry entry) {
			if (accessParameters == null)
				accessParameters = new UICCAccessApplicationSpecificParameters();
			this.accessParameters.addADFAccessEntry(entry);
			return this;
		}
		
		public Builder minimumSecurityLevel(byte minimumSecurityLevel) {
			this.toolkitParameters.setMinimumSecurityLevel(minimumSecurityLevel);
			return this;
		}
		
		public Builder maxNumberOfTimers(byte maxNumberOfTimers) {
			this.toolkitParameters.setMaxNumberOfTimers(maxNumberOfTimers);
			return this;
		}

		public Builder maxNumberOfServices(byte maxNumberOfServices) {
			this.toolkitParameters.setMaxNumberOfServices(maxNumberOfServices);
			return this;
		}

		public Builder maxNumberOfChannels(byte maxNumberOfChannels) {
			this.toolkitParameters.setMaxNumberOfChannels(maxNumberOfChannels);
			return this;
		}

		public Builder maxTextLengthForMenuEntry(byte maxTextLengthForMenuEntry) {
			this.toolkitParameters.setMaxTextLengthForMenuEntry(maxTextLengthForMenuEntry);
			return this;
		}
		
		public Builder priorityLevel(byte priorityLevel) {
			this.toolkitParameters.setPriorityLevel(priorityLevel);
			return this;
		}

		public Builder toolkitApplicationReference(byte [] toolkitApplicationReference) {
			this.toolkitParameters.setToolkitApplicationReference(toolkitApplicationReference);
			return this;
		}
		
		public Builder menuEntryList(List<ToolkitMenuEntry> menuEntries) {
			this.toolkitParameters.setMenuEntryList(menuEntries);
			return this;
		}
		
		public Builder addMenuEntry(ToolkitMenuEntry menuEntry) {
			this.toolkitParameters.addMenuEntry(menuEntry);
			return this;
		}
		
		public UICCAppletInstallParameters build() {
			UICCAppletInstallParameters output = new UICCAppletInstallParameters();
			
			output.sysParameters = new SystemSpecificParameters();
			output.sysParameters.setNonVolatileDataSpaceLimit(this.nonVolatileDataSpaceLimit);
			output.sysParameters.setVolatileDataSpaceLimit(this.volatileDataSpaceLimit);
			
			output.toolkitParameters  = this.toolkitParameters;
			output.accessParameters = this.accessParameters;
			output.appParameters = this.appParameters;
			
			return output;
		}
	}
	
}
