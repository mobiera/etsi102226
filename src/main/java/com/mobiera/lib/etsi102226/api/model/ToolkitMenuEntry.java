package com.mobiera.lib.etsi102226.api.model;

public class ToolkitMenuEntry {
	public int id;
	public int position;
	
	public ToolkitMenuEntry() {
		this(0,0);
	}
	public ToolkitMenuEntry(int i, int j){
		this.id = i;
		this.position = j;
	}
}
