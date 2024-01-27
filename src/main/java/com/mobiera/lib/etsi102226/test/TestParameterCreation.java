package com.mobiera.lib.etsi102226.test;

import java.io.IOException;

import com.mobiera.lib.etsi102226.api.model.STKAppletInstallParameters;
import com.mobiera.lib.etsi102226.api.model.ToolkitMenuEntry;
import com.mobiera.lib.etsi102226.api.model.UICCAppletInstallParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.ApplicationSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.STKAppletSystemSpecificParameters;

public class TestParameterCreation {

	public static final byte [] ACCESS_DOMAIN_FULL = {0x00};

	public static void main(String[] args) throws IOException {
		
		/* Example using regular SIM Toolkit applet install parameters */
		STKAppletInstallParameters installParams = 
				new STKAppletInstallParameters.Builder()
				.appParameters(new ApplicationSpecificParameters(
						ISOUtil.hex2byte("03E8008C008C020007060000112233445566778899AABBCCDDEEFF")))
				.sysParameters(
						new STKAppletSystemSpecificParameters.Builder()
							.accessDomain(ACCESS_DOMAIN_FULL)
							.addMenuEntry(new ToolkitMenuEntry())
							.addMenuEntry(new ToolkitMenuEntry())
							.addMenuEntry(new ToolkitMenuEntry())
							.build())
				.build();
		
		System.out.println("STK applet Parameters: " + ISOUtil.hexString(installParams.getBytes()));
	
		
		/* Example using UICC toolkit applet install parameters */
		UICCAppletInstallParameters uiccInstallParams = 
				new UICCAppletInstallParameters.Builder()
					.appParameters(
							new ApplicationSpecificParameters(ISOUtil.hex2byte("03E8008C008C020007060000112233445566778899AABBCCDDEEFF")))
					.uiccAccessDomain(ACCESS_DOMAIN_FULL)
					.toolkitApplicationReference("SLY".getBytes())
					.build();
		
		System.out.println("UICC applet Parameters: " + ISOUtil.hexString(uiccInstallParams.getBytes()));
		
	}

}
