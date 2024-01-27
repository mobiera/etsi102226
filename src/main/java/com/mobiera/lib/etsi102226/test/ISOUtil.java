package com.mobiera.lib.etsi102226.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.regex.Pattern;


public class ISOUtil {
	private static final Pattern HEX_CHARACTERS = Pattern.compile("[0-9a-fA-F]+");
	
	/**
     * converts a byte array to hex string 
     * (suitable for dumps and ASCII packaging of Binary fields
     * @param b - byte array
     * @return String representation
     */
    public static String hexString(byte[] b) {
        return hexString(b, 0, b.length);
    }

    public static String hexString(byte[] b, int offset, int len) {
        StringBuffer d = new StringBuffer(b.length * 2);
        for (int i=0; i<len; i++) {
            char hi = Character.forDigit ((b[offset+i] >> 4) & 0x0F, 16);
            char lo = Character.forDigit (b[offset+i] & 0x0F, 16);
            d.append(Character.toUpperCase(hi));
            d.append(Character.toUpperCase(lo));
        }
        return d.toString();
    }
    /**
     * @param   b       source byte array
     * @param   offset  starting offset
     * @param   len     number of bytes in destination (processes len*2)
     * @return  byte[len]
     */
    public static byte[] hex2byte (byte[] b, int offset, int len) {
        byte[] d = new byte[len];
        for (int i=0; i<len*2; i++) {
            int shift = i%2 == 1 ? 0 : 4;
            d[i>>1] |= Character.digit((char) b[offset+i], 16) << shift;
        }
        return d;
    }
    /**
     * @param s source string (with Hex representation)
     * @return byte array
     */
    public static byte[] hex2byte (String s) {
        if (s.length() % 2 == 0) {
            return hex2byte (s.getBytes(), 0, s.length() >> 1);
        } else {
            throw new RuntimeException();
        }
    }
    
    /**
     * converts a byte array to printable characters
     * @param b - byte array
     * @return String representation
     */
    public static String dumpString(byte[] b) {
        StringBuffer d = new StringBuffer(b.length * 2);
        for (int i=0; i<b.length; i++) {
            char c = (char) b[i];
            if (Character.isISOControl (c)) {
                // TODO: complete list of control characters,
                // use a String[] instead of this weird switch
                switch (c) {
                    case '\r'  : d.append ("{CR}");   break;
                    case '\n'  : d.append ("{LF}");   break;
                    case '\000': d.append ("{NULL}"); break;
                    case '\001': d.append ("{SOH}");  break;
                    case '\002': d.append ("{STX}");  break;
                    case '\003': d.append ("{ETX}");  break;
                    case '\004': d.append ("{EOT}");  break;
                    case '\005': d.append ("{ENQ}");  break;
                    case '\006': d.append ("{ACK}");  break;
                    case '\007': d.append ("{BEL}");  break;
                    case '\020': d.append ("{DLE}");  break;
                    case '\025': d.append ("{NAK}");  break;
                    case '\026': d.append ("{SYN}");  break;
                    case '\034': d.append ("{FS}");  break;
                    case '\036': d.append ("{RS}");  break;
                    default:
                        char hi = Character.forDigit ((b[i] >> 4) & 0x0F, 16);
                        char lo = Character.forDigit (b[i] & 0x0F, 16);
                        d.append('[');
                        d.append(Character.toUpperCase(hi));
                        d.append(Character.toUpperCase(lo));
                        d.append(']');
                        break;
                }
            }
            else
                d.append (c);

        }
        return d.toString();
    }
    
    public static boolean isValidHexString(String s) {
    	return HEX_CHARACTERS.matcher(s).matches();
    }
    
    /**
     * Swaps nibbles of all bytes in an input array
     * 
     * @param value
     * @return
     */
    public static byte [] swapNibbles(byte [] value) {
    	byte [] output = new byte[value.length];
   
    	for (int i = 0 ; i < output.length ; i++) {
    		int nibble0 = (value[i] << 4) & 0xf0;
            int nibble1 = (value[i] >>> 4) & 0x0f;
            output[i] = (byte)((nibble0 | nibble1));
    	}
    	System.out.println(ISOUtil.hexString(output));
    	return output;
    }
    
    public static byte [] padAndSwapNibbles(String value) {
    	String paddedValue = new String(value);
		if (paddedValue.length() % 2 != 0)
			paddedValue += "F";
    	return swapNibbles(hex2byte(paddedValue
    			.replace("*", "A")
    			.replace("#", "B")));
    }
    
    public static byte [] swapNibbles(String value) {
    	return swapNibbles(hex2byte(value));
    }

    
    
    /** 
	 * Encodes an Alpha identifier or Text string as in EF_ADN (UCS2 coding
	 * definition in Annex A from TS 102.221 (case 1)
	 * 
	 * @param dcs
	 * @param text
	 * @return
	 * @throws IOException
	 */
	public static byte [] encodeAlphaId(byte dcs, String text) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			if (dcs == 0x08) {
				output.write((byte)0x80);
				output.write(text.getBytes("UTF-16BE"));
			} else {
				output.write(text.getBytes("UTF-8"));
			}	
		} catch (UnsupportedEncodingException e) {}
		
		return output.toByteArray();
	}
	
	 /** 
		 * Decodes an Alpha identifier or Text string as in EF_ADN (UCS2 coding
		 * definition in Annex A from TS 102.221 (case 1)
		 * 
		 * @param dcs
		 * @param text
		 * @return
		 * @throws IOException
		 */
		public static String decodeAlphaId(byte [] text) throws IOException {
			
			try {
				// UCS2
				if (text[0] == (byte)0x80) {
					return new String(Arrays.copyOfRange(text, 1, text.length),
							"UTF-16BE");
				} else {
					return new String(text);
				}
				
			} catch (UnsupportedEncodingException e) {}
		return null;
		}
	
	/** 
	 * Encodes a Text string TLV value as defined in TS 102.223
	 * 
	 * @param dcs
	 * @param text
	 * @return
	 * @throws IOException
	 */
	public static byte [] encodeTextString(byte dcs, String text) 
			throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			output.write((byte)dcs);
			if (dcs == 0x08) {
				output.write(text.getBytes("UTF-16BE"));
			} else {
				output.write(text.getBytes("UTF-8"));
			}	
		} catch (UnsupportedEncodingException e) {

		}
		
		return output.toByteArray();
	}
	
	public static String decodeTextString(byte [] tlvValue) throws IOException {
		
		byte dcs = tlvValue[0];
		
		String charsetName = "UTF-8";

		if (dcs == 0x08) {
			charsetName = "UTF-16BE";
		} 
		byte [] value = Arrays.copyOfRange(tlvValue, 1, tlvValue.length);
		
		return new String(value, charsetName);
	}
}