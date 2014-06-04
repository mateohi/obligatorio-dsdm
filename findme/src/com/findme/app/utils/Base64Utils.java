package com.findme.app.utils;

import android.util.Base64;

public class Base64Utils {

	public static byte[] base64StringToBytes(String dataBase64) {
		byte[] dataBytes = Base64.decode(dataBase64, Base64.DEFAULT);
		
		return dataBytes;
	}
	
	public static String bytesToBase64String(byte[] dataBytes) {
		String dataBase64 = Base64.encodeToString(dataBytes, Base64.DEFAULT);
		
		return dataBase64;
	}
}
