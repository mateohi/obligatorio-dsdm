package com.findme.service;

import com.findme.service.utilities.base64.Base64Exception;
import com.findme.service.utilities.base64.ManejadorBase64;

public class TestBase64 {
	public static void main(String[] args) throws Base64Exception {
		String base64 = ManejadorBase64.fileABase64("C://auto.jpg", "jpg");

		System.out.println(base64);

		ManejadorBase64.base64AFile(base64, "C://test//auto_nuevo.jpg", "jpg");
	}
}
