package com.mobiera.lib.etsi102226.api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.mobiera.lib.etsi102226.api.model.tlv.ApplicationSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.SystemSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.UICCAccessApplicationSpecificParameters;
import com.mobiera.lib.etsi102226.api.model.tlv.UICCToolkitApplicationSpecificParameters;
import com.mobiera.lib.etsi102226.test.ISOUtil;

class InstallParametersEncodingTest {

	private static final ApplicationSpecificParameters NO_APP_PARAMS =
			new ApplicationSpecificParameters(new byte[0]);

	@Test
	void memoryQuotasAreMostSignificantByteFirst() throws Exception {
		SystemSpecificParameters sys = new SystemSpecificParameters();
		sys.setVolatileDataSpaceLimit(0x0100);
		sys.setNonVolatileDataSpaceLimit(0x0200);

		assertEquals("EF08C7020100C8020200", ISOUtil.hexString(sys.getBytes()));
	}

	@Test
	void uiccParametersAreNestedInSystemSpecificParameters() throws Exception {
		UICCAppletInstallParameters params = new UICCAppletInstallParameters.Builder()
				.appParameters(NO_APP_PARAMS)
				.nonVolatileDataSpaceLimit(0x0200)
				.volatileDataSpaceLimit(0x0100)
				.minimumSecurityLevel((byte) 0)
				.maxNumberOfTimers((byte) 0)
				.maxTextLengthForMenuEntry((byte) 20)
				.priorityLevel((byte) 0)
				.menuEntryList(Collections.singletonList(new ToolkitMenuEntry()))
				.uiccAccessDomain(new byte[] { 0x00 })
				.toolkitApplicationReference("MIU".getBytes())
				.build();

		// EF { C7 VRAM, C8 NVM, EA { 80 toolkit, 81 access } } C9
		assertEquals("EF20" + "C7020100" + "C8020200"
				+ "EA16"
				+ "800E" + "000014010000000100034D495500"
				+ "8104" + "00010000"
				+ "C900", ISOUtil.hexString(params.getBytes()));
	}

	@Test
	void uiccAccessParametersAreOptional() throws Exception {
		UICCAppletInstallParameters params = new UICCAppletInstallParameters.Builder()
				.appParameters(NO_APP_PARAMS)
				.nonVolatileDataSpaceLimit(0x1000)
				.volatileDataSpaceLimit(0x0040)
				.build();

		assertEquals("EF15C7020040C8021000EA0B8009FF0014000001000000C900",
				ISOUtil.hexString(params.getBytes()));
	}

	@Test
	void uiccToolkitParametersWithoutBuilder() throws Exception {
		UICCAppletInstallParameters params = new UICCAppletInstallParameters(
				NO_APP_PARAMS, new UICCToolkitApplicationSpecificParameters());

		assertEquals("EF0DEA0B8009FF0014000001000000C900", ISOUtil.hexString(params.getBytes()));
	}

	@Test
	void uiccToolkitParametersEndWithMaxNumberOfServices() throws Exception {
		UICCToolkitApplicationSpecificParameters toolkit = new UICCToolkitApplicationSpecificParameters();
		toolkit.setMaxNumberOfChannels((byte) 2);
		toolkit.setMaxNumberOfServices((byte) 5);

		// priority, timers, text length, 0 menu entries, channels, MSL LV, TAR LV, services
		assertEquals("8009FF0014000201000005", ISOUtil.hexString(toolkit.getBytes()));
	}

	@Test
	void uiccAccessDomainDapIsEncoded() throws Exception {
		UICCAccessApplicationSpecificParameters.AccessDomainEntry entry =
				new UICCAccessApplicationSpecificParameters.AccessDomainEntry(
						ISOUtil.hex2byte("A0000000871002"), new byte[] { 0x00 }, new byte[] { 0x11, 0x22 });

		assertEquals("07A00000008710020100021122", ISOUtil.hexString(entry.getBytes()));
	}
}
