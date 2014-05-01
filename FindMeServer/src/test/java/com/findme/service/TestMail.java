/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.findme.service;

import com.findeme.service.utilities.ManejadorMail;
import com.findeme.service.utilities.mail.MailGeneratorException;
import com.findeme.service.utilities.qr.QRGeneratorException;
import java.io.IOException;

public class TestMail {

    public static void main(String[] args) throws Exception {
        try {
            ManejadorMail.enviarQR("Guido fag", "guidocorazza36@hotmail.com", "project.properties");
            System.out.println("Mail enviado");
        } catch (QRGeneratorException ex) {
            System.out.println("Error al generar el QR");
        } catch (MailGeneratorException ex) {
            System.out.println("Error al generar el email");
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo properties");
        }
    }
}
