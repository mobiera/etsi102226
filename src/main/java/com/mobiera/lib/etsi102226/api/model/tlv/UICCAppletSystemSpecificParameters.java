package com.mobiera.lib.etsi102226.api.model.tlv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * System specific parameters for UICC toolkit applets, as defined in ETSI 102.226
 *
 * It adds a UICCSystemSpecificParameters TLV (tag EA, carrying the UICC Toolkit
 * and UICC Access TLVs) after the data space limits, all within tag EF
 *
 */
public class UICCAppletSystemSpecificParameters extends SystemSpecificParameters  {

	UICCSystemSpecificParameters uiccSysParams;

	public UICCAppletSystemSpecificParameters() {
		super();
		uiccSysParams = new UICCSystemSpecificParameters();
	}

	public void setUICCToolkitParameters(UICCToolkitApplicationSpecificParameters toolkitParams) {
		uiccSysParams.toolkitParams = toolkitParams;
	}

	public void setUICCAccessParameters(UICCAccessApplicationSpecificParameters accessParams) {
		uiccSysParams.accessParams = accessParams;
	}

	@Override
	public byte [] getValue() throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();

		// Take base TLVs (non volatile and volatile data space)
		bo.write(super.getValue());

		// Add UICC System Specific Parameters TLV
		bo.write(uiccSysParams.getBytes());
		return bo.toByteArray();

	}

}
