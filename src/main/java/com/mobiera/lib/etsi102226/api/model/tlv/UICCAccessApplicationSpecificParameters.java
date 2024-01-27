package com.mobiera.lib.etsi102226.api.model.tlv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UICCAccessApplicationSpecificParameters extends SimpleTLV {

	public static class AccessDomainEntry {

		private byte [] adfAid;
		private byte [] accessDomain;
		private byte [] accessDomainDap;

		public AccessDomainEntry() {
			this.accessDomain = new byte[1];
		}
		
		public AccessDomainEntry(byte [] adfAid, byte [] accessDomain) {
			this(adfAid,accessDomain,null);
		}
		
		public AccessDomainEntry(byte [] adfAid, byte [] accessDomain, 
				byte [] accessDomainDap) {
			this.adfAid = adfAid;
			this.accessDomain = accessDomain;
			this.accessDomainDap = accessDomainDap;
		}

		public byte [] getBytes() throws IOException {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();

			// AID LV
			if (adfAid != null) {
				bo.write((byte)adfAid.length); // ADF AID Length
				bo.write(adfAid);
			} else {
				bo.write((byte)0); // ADF AID Length
			}

			// Access Domain LV
			bo.write((byte)accessDomain.length);
			bo.write(accessDomain);

			// Access Domain DAP LV
			if (accessDomainDap != null) {
				bo.write((byte)accessDomainDap.length); // Access Domain DAP Length
				bo.write(adfAid); // Acces Domain value
			} else {
				bo.write((byte)0); // Access Domain DAP Length
			}

			return bo.toByteArray();
		}
	}

	public static int TAG = 0x81;

	private List<AccessDomainEntry> entries;

	private AccessDomainEntry uiccFsEntry;
	
	public void setUICCFileSystemAccessDomain(byte [] accessDomain) {
		this.uiccFsEntry = new AccessDomainEntry();
		this.uiccFsEntry.accessDomain = accessDomain;
			
	}

	public void addADFAccessEntry(AccessDomainEntry entry) {
		this.entries.add(entry);
	}

	public UICCAccessApplicationSpecificParameters(){
		super(TAG);

		entries = new ArrayList<AccessDomainEntry>();

	}

	@Override
	public byte [] getValue() throws IOException {

		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		
		if (uiccFsEntry != null) {
			bo.write(uiccFsEntry.getBytes());
		}
		
		if (entries.size() > 0) {
			for (AccessDomainEntry entry : entries)
			{
				bo.write(entry.getBytes());
			}
		}

		return bo.toByteArray();
	}

}
