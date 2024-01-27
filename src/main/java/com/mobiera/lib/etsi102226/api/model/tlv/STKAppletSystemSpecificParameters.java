package com.mobiera.lib.etsi102226.api.model.tlv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.mobiera.lib.etsi102226.api.model.ToolkitMenuEntry;

/**
 * 
 * System specific parameters for STK applets, as defined in ETSI 102.226
 * 
 * It adds a SIMToolkitApplicationSpecificParameters TLV and includes a handy
 * builder for any STK applet
 * 
 * @author Ariel Gentile
 *
 */
public class STKAppletSystemSpecificParameters extends SystemSpecificParameters  {

	SIMToolkitApplicationSpecificParameters stkSysParams;
	
	public STKAppletSystemSpecificParameters() {
		super();
		stkSysParams = new SIMToolkitApplicationSpecificParameters();
	}
	
	@Override
	public byte [] getValue() throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		
		// Take base TLVs (non volatile and volatile data space)
		bo.write(super.getValue());
		
		// Add specific TLV
		bo.write(stkSysParams.getBytes());
		return bo.toByteArray();
		
	}
	
	/**
	 * Builder for STKAppletSystemParameters. Handy for most STK applets.
	 * 
	 * @author Ariel Gentile
	 *
	 */
	public static class Builder {
		
		private Integer nonVolatileDataSpaceLimit; // = 0;
		private Integer volatileDataSpaceLimit; // = 0;
		private SIMToolkitApplicationSpecificParameters stkParameters;
		
		public Builder() {
			stkParameters = new SIMToolkitApplicationSpecificParameters();
		}
		
		public Builder nonVolatileDataSpaceLimit(Integer limit) {
			this.nonVolatileDataSpaceLimit = limit;
			return this;
		}
		
		public Builder volatileDataSpaceLimit(Integer limit) {
			this.volatileDataSpaceLimit = limit;
			return this;
		}
		
		public Builder accessDomain(byte [] accessDomain) {
			this.stkParameters.setAccessDomain(accessDomain);
			return this;
		}
		
		public Builder minimumSecurityLevel(Byte minimumSecurityLevel) {
			this.stkParameters.setMinimumSecurityLevel(minimumSecurityLevel);
			return this;
		}
		
		public Builder maxNumberOfTimers(byte maxNumberOfTimers) {
			this.stkParameters.setMaxNumberOfTimers(maxNumberOfTimers);
			return this;
		}
		
		public Builder maxTextLengthForMenuEntry(byte maxTextLengthForMenuEntry) {
			this.stkParameters.setMaxTextLengthForMenuEntry(maxTextLengthForMenuEntry);
			return this;
		}
		
		public Builder priorityLevel(byte priorityLevel) {
			this.stkParameters.setPriorityLevel(priorityLevel);
			return this;
		}

		public Builder toolkitApplicationReference(byte [] toolkitApplicationReference) {
			this.stkParameters.setToolkitApplicationReference(toolkitApplicationReference);
			return this;
		}
		
		public Builder menuEntryList(List<ToolkitMenuEntry> menuEntries) {
			this.stkParameters.setMenuEntryList(menuEntries);
			return this;
		}
		
		public Builder addMenuEntry(ToolkitMenuEntry menuEntry) {
			this.stkParameters.addMenuEntry(menuEntry);
			return this;
		}
		
		public STKAppletSystemSpecificParameters build() {
			STKAppletSystemSpecificParameters output = new STKAppletSystemSpecificParameters();
			
			output.nonVolatileDataSpaceLimit = this.nonVolatileDataSpaceLimit;
			output.volatileDataSpaceLimit =this.volatileDataSpaceLimit;
			  
			output.stkSysParams = this.stkParameters;
			
			return output;
		}
	}
	
	
}
