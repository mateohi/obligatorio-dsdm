package com.findme.service.utilities.base64;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;

public class ManejadorBase64 {

	public static void base64AFile(String base64, String path, String type) throws Base64Exception {
		Base64 decoder = new Base64();
        
        try {
        	byte[] imageByte = decoder.decode(base64);
        	
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            BufferedImage image = ImageIO.read(bis);
            bis.close();
            
            ImageIO.write(image, type, new File(path));
        } catch (Exception e) {
        	throw new Base64Exception(Base64Exception.DECODIFICAR);
        }
	}
	
	public static String fileABase64(String path, String type) throws Base64Exception {
		Base64 encoder = new Base64();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		try {
			BufferedImage img = ImageIO.read(new File(path));
			ImageIO.write(img, type, bos);
            byte[] imageBytes = bos.toByteArray();
            String imageString = encoder.encodeToString(imageBytes);
            bos.close();
            
            return imageString;
		} catch (IOException e) {
			throw new Base64Exception(Base64Exception.CODIFICAR);
		}
	}
}
