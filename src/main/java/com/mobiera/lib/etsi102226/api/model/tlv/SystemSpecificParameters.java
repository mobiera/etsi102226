package com.mobiera.lib.etsi102226.api.model.tlv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 
 * Base class for system specific parameters, as defined in Global Platform 2.1.1
 * 
 * Specific applet types (e.g. STK applets) shall extend this class to include
 * their particular TLVs
 * 
 * @author Ariel Gentile
 *
 */
public class SystemSpecificParameters extends SimpleTLV {

	protected static int TAG = 0xEF;
	
	protected Integer nonVolatileDataSpaceLimit;
	protected Integer volatileDataSpaceLimit;
	
	
	public void setNonVolatileDataSpaceLimit(Integer limit) {
		this.nonVolatileDataSpaceLimit = limit;
	}
	
	public void setVolatileDataSpaceLimit(Integer limit) {
		this.volatileDataSpaceLimit = limit;
	}
	
	public SystemSpecificParameters() {
		super(TAG);
	}
	
	@Override
	public byte [] getValue() throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		
		// Volatile data space limit TLV
		if (volatileDataSpaceLimit != null) {
			bo.write(0xC7);
			bo.write(2);
			bo.write((byte) volatileDataSpaceLimit.intValue());
			bo.write((byte) (volatileDataSpaceLimit.intValue() >>> 8));
			
		}
		
		// Non Volatile data space limit TLV
		if (nonVolatileDataSpaceLimit != null) {
			bo.write(0xC8);
			bo.write(2);
			bo.write((byte) nonVolatileDataSpaceLimit.intValue());
			bo.write((byte) (nonVolatileDataSpaceLimit.intValue() >>> 8));
			
		}
		
		return bo.toByteArray();
		
	}
	
	
	
	
}
