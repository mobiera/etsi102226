package com.mobiera.lib.etsi102226.api.model.tlv;


/**
 * 
 * Base class for Application Specific Parameters. Each applet profile may adapt it
 * according to its convenience.
 * 
 * @author Ariel Gentile
 *
 */
public class ApplicationSpecificParameters extends SimpleTLV {

	protected static int TAG = 0xC9;
	protected byte [] value;
	
	public ApplicationSpecificParameters() {
		super(TAG);
	}
	
	public ApplicationSpecificParameters(byte [] value) {
		super(TAG,value);
	}
	
	public void setValue(byte [] value) {
		this.value = value;
	}
}
