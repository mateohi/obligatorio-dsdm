package com.findeme.service.utilities.qr;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

public class GeneradorQR {

    private static final String CHARSET = "UTF-8";
    private static final int HEIGHT = 200;
    private static final int WIDTH = 200;

    public static byte[] crearQRChico(String datos) throws QRGeneratorException{        
        try {
            return createQRCode(datos, CHARSET, mapa(), HEIGHT, WIDTH);
        }
        catch(WriterException ex) {
            throw new QRGeneratorException();
        }
        catch(IOException ex) {
            throw new QRGeneratorException();
        }
    }

    private static byte[] createQRCode(String datos, String charset,
            Map hintMap, int height, int width) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(datos.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height, hintMap);

        BufferedImage img = MatrixToImageWriter.toBufferedImage(matrix);

        return imageToBytes(img);
    }

    private static Map<EncodeHintType, ErrorCorrectionLevel> mapa() {
        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        return hintMap;
    }

    private static byte[] imageToBytes(BufferedImage img) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "png", baos);
        baos.flush();
        byte[] imageBytes = baos.toByteArray();
        baos.close();
        
        return imageBytes;
    }
}