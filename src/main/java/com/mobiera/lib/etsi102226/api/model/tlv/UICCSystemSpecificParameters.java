package com.mobiera.lib.etsi102226.api.model.tlv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.mobiera.lib.etsi102226.api.model.ToolkitMenuEntry;

/**
 * 
 * System specific parameters for UICC applets, as defined in ETSI 102.226
 *  
 * @author Ariel Gentile
 *
 */
public class UICCSystemSpecificParameters extends SimpleTLV  {

	protected static int TAG = 0xEA;
	
	UICCToolkitApplicationSpecificParameters toolkitParams;
	UICCAccessApplicationSpecificParameters accessParams;
	
	
	public UICCSystemSpecificParameters() {
		super(TAG);
		toolkitParams = new UICCToolkitApplicationSpecificParameters();
	}
	
	@Override
	public byte [] getValue() throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		
		// Add specific UICC Toolkit TLV
		bo.write(toolkitParams.getBytes());
		
		// Add specific UICC Access TLV
		bo.write(accessParams.getBytes());
		return bo.toByteArray();
		
	}
	
	/**
	 * Builder for STKAppletSystemParameters. Handy for most STK applets.
	 * 
	 * @author Ariel Gentile
	 *
	 */
	public static class Builder {
		
		private UICCToolkitApplicationSpecificParameters toolkitParameters;
		private UICCAccessApplicationSpecificParameters accessParameters;
		
		public Builder() {
			toolkitParameters = new UICCToolkitApplicationSpecificParameters();
		}
		
		public Builder accessParameters(UICCAccessApplicationSpecificParameters accessParams) {
			this.accessParameters = accessParams;
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
		
		public UICCSystemSpecificParameters build() {
			UICCSystemSpecificParameters output = new UICCSystemSpecificParameters();
			
			output.accessParams = this.accessParameters;
			output.toolkitParams = this.toolkitParameters;
			
			return output;
		}
	}
	
	
}
