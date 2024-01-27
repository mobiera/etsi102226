package com.mobiera.lib.etsi102226.test;

import java.io.IOException;

import com.mobiera.lib.etsi102226.api.model.ToolkitMenuEntry;
import com.mobiera.lib.etsi102226.api.model.tlv.STKAppletSystemSpecificParameters;

public class TestStkParams {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		STKAppletSystemSpecificParameters param = new STKAppletSystemSpecificParameters.Builder()
				.addMenuEntry(new ToolkitMenuEntry(17,17))
				.addMenuEntry(new ToolkitMenuEntry(18,18))
				.addMenuEntry(new ToolkitMenuEntry(19,19))
				.addMenuEntry(new ToolkitMenuEntry(20,20))
				.addMenuEntry(new ToolkitMenuEntry(21,21))
				.addMenuEntry(new ToolkitMenuEntry(22,22))
				.addMenuEntry(new ToolkitMenuEntry(23,23))
				.maxTextLengthForMenuEntry((byte) 32)
				
				.build();
		
		System.out.println(ISOUtil.hexString(param.getBytes()));
		
		System.out.println("EF22C8020001C7020001CA180100FF002007111112121313141415151616171700020100");
	}

}
