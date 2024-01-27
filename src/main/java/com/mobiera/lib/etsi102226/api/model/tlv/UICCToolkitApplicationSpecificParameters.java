package com.mobiera.lib.etsi102226.api.model.tlv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mobiera.lib.etsi102226.api.model.ToolkitMenuEntry;

public class UICCToolkitApplicationSpecificParameters extends SimpleTLV {

	public static int TAG = 0x80;
	
	private byte priorityLevel;
	private byte maxNumberOfTimers;
	private byte maxTextLengthForMenuEntry;
	private List<ToolkitMenuEntry> menuEntries;
	private byte maxNumberOfChannels;
	private byte minimumSecurityLevel;
	private byte [] toolkitApplicationReference;
	private byte maxNumberOfServices;


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
	
	public void addMenuEntry(ToolkitMenuEntry menuEntry) {
		this.menuEntries.add(menuEntry);
	}

	public UICCToolkitApplicationSpecificParameters(){
		super(TAG);
		
		priorityLevel = (byte) 255;
		maxNumberOfTimers = 0;
		maxTextLengthForMenuEntry = 20;
		menuEntries = new ArrayList<ToolkitMenuEntry>();
		maxNumberOfChannels = 0;
		
		minimumSecurityLevel = 0;
		
		maxNumberOfServices = 0;
		
	}

	@Override
	public byte [] getValue() throws IOException {
		
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		bo.write(priorityLevel);
		bo.write(maxNumberOfTimers);
		bo.write(maxTextLengthForMenuEntry);
		bo.write(menuEntries.size());

		for (ToolkitMenuEntry entry : menuEntries)
		{
			bo.write(entry.id);
			bo.write(entry.position);
		}
		
		bo.write(maxNumberOfChannels);
		
		// MSL Length
		bo.write((byte)1);
		bo.write(minimumSecurityLevel);
		
		if (toolkitApplicationReference != null) {
			bo.write(toolkitApplicationReference.length);
			bo.write(toolkitApplicationReference);
		} else {
			bo.write((byte)0);
		}

		bo.write(maxNumberOfChannels);
		
		return bo.toByteArray();
	}


	public byte getMinimumSecurityLevel() {
		return minimumSecurityLevel;
	}


	public void setMinimumSecurityLevel(byte minimumSecurityLevel) {
		this.minimumSecurityLevel = minimumSecurityLevel;
	}


	public byte [] getToolkitApplicationReference() {
		return toolkitApplicationReference;
	}


	public void setToolkitApplicationReference(byte [] toolkitApplicationReference) {
		this.toolkitApplicationReference = toolkitApplicationReference;
	}


	public byte getMaxNumberOfChannels() {
		return maxNumberOfChannels;
	}


	public void setMaxNumberOfChannels(byte maxNumberOfChannels) {
		this.maxNumberOfChannels = maxNumberOfChannels;
	}


	public byte getMaxNumberOfServices() {
		return maxNumberOfServices;
	}


	public void setMaxNumberOfServices(byte maxNumberOfServices) {
		this.maxNumberOfServices = maxNumberOfServices;
	}


}
