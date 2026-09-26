package com.mobiera.lib.etsi102226.api.model;

import java.util.List;

import com.mobiera.lib.etsi102226.api.model.tlv.ApplicationSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.SystemSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.UICCAccessApplicationSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.UICCAppletSystemSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.UICCToolkitApplicationSpecificParameters;

/**
 * Base class for Install Parameters field according to ETSI 102.226
 *
 * The UICC Toolkit (80) and UICC Access (81) TLVs are carried in the UICC System
 * Specific Parameters (EA), within the System Specific Parameters (EF):
 * EF { C7, C8, EA { 80, 81 } } followed by C9
 *
 * Each applet type shall extend this class
 *
 * @author genaris
 *
 */
public class UICCAppletInstallParameters extends InstallParameters {

	protected UICCAppletSystemSpecificParameters sysParameters;

	public UICCAppletInstallParameters() {
		super();
		sysParameters = new UICCAppletSystemSpecificParameters();
	}

	public UICCAppletInstallParameters(ApplicationSpecificParameters appParams,
			UICCToolkitApplicationSpecificParameters uiccParams) {
		this();
		setAppParameters(appParams);
		setUICCToolkitParameters(uiccParams);
	}

	public void setUICCToolkitParameters(UICCToolkitApplicationSpecificParameters uiccParams) {
		this.sysParameters.setUICCToolkitParameters(uiccParams);
	}

	public void setUICCAccessParameters(UICCAccessApplicationSpecificParameters accessParams) {
		this.sysParameters.setUICCAccessParameters(accessParams);
	}

	@Override
	protected SystemSpecificParameters getSystemSpecificParameters() {
		return this.sysParameters;
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
			this.volatileDataSpaceLimit = limit;
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
			
			output.sysParameters.setNonVolatileDataSpaceLimit(this.nonVolatileDataSpaceLimit);
			output.sysParameters.setVolatileDataSpaceLimit(this.volatileDataSpaceLimit);

			output.setUICCToolkitParameters(this.toolkitParameters);
			output.setUICCAccessParameters(this.accessParameters);
			output.appParameters = this.appParameters;
			
			return output;
		}
	}
	
}
