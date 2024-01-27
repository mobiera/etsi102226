package com.mobiera.lib.etsi102226.api;

public class EtsiConstants {

	public static final byte TAG_CMD_SCRIPTING_TEMPLATE_DEFINITE_LEN 	= (byte)0xAA;
	public static final byte TAG_RSP_SCRIPTING_TEMPLATE_DEFINITE_LEN 	= (byte)0xAB;
	public static final byte TAG_CMD_SCRIPTING_TEMPLATE_INDEFINITE_LEN	= (byte)0xAE;
	public static final byte TAG_RSP_SCRIPTING_TEMPLATE_INDEFINITE_LEN	= (byte)0xAF;
	public static final byte TAG_NUMBER_OF_EXECUTED_COMMAND_TLVS 		= (byte)0x80;
	public static final byte TAG_BAD_FORMAT_TLV 						= (byte)0x90;
	public static final byte TAG_IMMEDIATE_ACTION 						= (byte)0x81;
	public static final byte TAG_IMMEDIATE_ACTION_RESPONSE 				= (byte)0x81;
	public static final byte TAG_ERROR_ACTION 							= (byte)0x82;
	public static final byte TAG_SCRIPT_CHAINING 						= (byte)0x83;
	public static final byte TAG_SCRIPT_CHAINING_RESPONSE 				= (byte)0x83;
	
	public static final byte TAG_C_APDU 								= (byte)0x22;
	public static final byte TAG_R_APDU 								= (byte)0x23;
}
