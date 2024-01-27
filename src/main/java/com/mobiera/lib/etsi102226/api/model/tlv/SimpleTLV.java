package com.mobiera.lib.etsi102226.api.model.tlv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SimpleTLV {

	protected int tag;
	
	protected byte [] value;
	
	public SimpleTLV(int tag) {
		this(tag, new byte[0]);
	}

	public SimpleTLV(int tag, byte [] value) {
		this.tag = tag;
		this.value = value;
	}
	
	public int getTag() {
		return this.tag;
	}
	
	public byte [] getValue() throws IOException {
		return this.value;
	}
	
	public byte [] getBytes() throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		
		byte [] value = getValue();
		bo.write(getTag());
		
		if (value != null) {			
			bo.write(value.length);
			bo.write(value);
		}
		else {
			bo.write(0);
		}
		return bo.toByteArray();
		
	}
}
