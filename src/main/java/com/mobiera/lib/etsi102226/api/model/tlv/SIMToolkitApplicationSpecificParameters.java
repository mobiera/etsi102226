package com.mobiera.lib.etsi102226.api.model.tlv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mobiera.lib.etsi102226.api.model.ToolkitMenuEntry;

public class SIMToolkitApplicationSpecificParameters extends SimpleTLV {

	public static int TAG = 0xCA;
	
	private byte [] accessDomain;
	private byte priorityLevel;
	private byte maxNumberOfTimers;
	private byte maxTextLengthForMenuEntry;
	private List<ToolkitMenuEntry> menuEntries;
	private Byte minimumSecurityLevel;
	private byte [] toolkitApplicationReference;
	
	public byte[] getAccessDomain() {
		return accessDomain;
	}


	public void setAccessDomain(byte[] accessDomain) {
		this.accessDomain = accessDomain;
	}


	public byte getPriorityLevel() {
		return priorityLevel;
	}


	public void setPriorityLevel(byte priorityLevel) {
		this.priorityLevel = priorityLevel;
	}


	public byte getMaxNumberOfTimers() {
		return maxNumberOfTimers;
	}


	public void setMaxNumberOfTimers(byte maxNumberOfTimers) {
		this.maxNumberOfTimers = maxNumberOfTimers;
	}

	public byte getMaxTextLengthForMenuEntry() {
		return maxTextLengthForMenuEntry;
	}

	public void setMaxTextLengthForMenuEntry(byte maxTextLengthForMenuEntry) {
		this.maxTextLengthForMenuEntry = maxTextLengthForMenuEntry;
	}

	public void setMenuEntryList(List<ToolkitMenuEntry> entries){
		this.menuEntries.clear();
		this.menuEntries.addAll(entries);
	}
	
	public void addMenuEntry(ToolkitMenuEntry entry) {
		this.menuEntries.add(entry);
	}

	public SIMToolkitApplicationSpecificParameters(){
		super(TAG);
		accessDomain = new byte [1]; // Full Access
		priorityLevel = (byte) 255;
		maxNumberOfTimers = 0;
		maxTextLengthForMenuEntry = 32;
		menuEntries = new ArrayList<ToolkitMenuEntry>();
		minimumSecurityLevel = 0;
	}

	@Override
	public byte [] getValue() throws IOException {
		
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		
		if (accessDomain != null) {
			bo.write(accessDomain.length);
			bo.write(accessDomain);			
		} else {
			bo.write((byte)1);
			bo.write((byte)0); // Full access domain
		}
		
		bo.write(priorityLevel);
		bo.write(maxNumberOfTimers);
		bo.write(maxTextLengthForMenuEntry);
		bo.write(menuEntries.size());

		for (ToolkitMenuEntry entry : menuEntries)
		{
			bo.write(entry.id);
			bo.write(entry.position);
		}
		
		// MSL Length
		if (minimumSecurityLevel!=null) {
			bo.write((byte)1);
			bo.write(minimumSecurityLevel);
			
		}
		
		if (toolkitApplicationReference != null)
			bo.write(toolkitApplicationReference);
		
		return bo.toByteArray();
	}


	public byte getMinimumSecurityLevel() {
		return minimumSecurityLevel;
	}


	public void setMinimumSecurityLevel(Byte minimumSecurityLevel) {
		this.minimumSecurityLevel = minimumSecurityLevel;
	}


	public byte [] getToolkitApplicationReference() {
		return toolkitApplicationReference;
	}


	public void setToolkitApplicationReference(byte [] toolkitApplicationReference) {
		this.toolkitApplicationReference = toolkitApplicationReference;
	}

}
