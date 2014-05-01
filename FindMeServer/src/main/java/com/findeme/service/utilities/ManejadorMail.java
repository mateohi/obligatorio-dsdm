package com.findeme.service.utilities;

import com.findeme.service.utilities.mail.GeneradorMail;
import com.findeme.service.utilities.mail.MailGeneratorException;
import com.findeme.service.utilities.qr.GeneradorQR;
import com.findeme.service.utilities.qr.QRGeneratorException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ManejadorMail {

    public static void enviarQR(String idMascota, String mailTo, String dirProps)
            throws QRGeneratorException, MailGeneratorException, IOException {

        byte[] imagenBytes = GeneradorQR.crearQRChico(idMascota);

        Properties props = new Properties();
        props.load(new FileInputStream(dirProps));

        GeneradorMail generador = new GeneradorMail(props);
        generador.enviarConAdjunto(mailTo, imagenBytes, "CodigoQR: " + idMascota);
    }
}
