package com.findme.service.utilities;

import com.findme.service.utilities.mail.GeneradorMail;
import com.findme.service.utilities.mail.MailGeneratorException;
import com.findme.service.utilities.qr.GeneradorQR;
import com.findme.service.utilities.qr.QRGeneratorException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ManejadorMail {

    public static void enviarQR(String idUsuario, String nombreMascota, String mailTo, String dirProps)
            throws QRGeneratorException, MailGeneratorException, IOException {

        byte[] imagenBytes = GeneradorQR.crearQRChico(idUsuario + "+" + nombreMascota);

        Properties props = new Properties();
        props.load(new FileInputStream(dirProps));

        GeneradorMail generador = new GeneradorMail(props);
        generador.enviarConAdjunto(mailTo, imagenBytes, "CodigoQR: " + nombreMascota);
    }
}
