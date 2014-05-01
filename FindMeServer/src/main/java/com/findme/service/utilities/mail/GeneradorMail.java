package com.findme.service.utilities.mail;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class GeneradorMail {

    private static final String MAIL = "mail";
    private static final String PASS = "pass";
    private static final String HOST = "host";
    private static final String PORT = "port";
    private static final String SUBJECT = "subject";
    private static final String TEXT = "text";
    private String mail;
    private String pass;
    private String host;
    private String port;
    private String subject;
    private String text;

    private void loadProperties(Properties props) {
        this.mail = props.getProperty(MAIL);
        this.pass = props.getProperty(PASS);
        this.host = props.getProperty(HOST);
        this.port = props.getProperty(PORT);
        this.subject = props.getProperty(SUBJECT);
        this.text = props.getProperty(TEXT);
    }

    public GeneradorMail(Properties props) throws MailGeneratorException {
        if (!valido(props)) {
            throw new MailGeneratorException();
        }

        loadProperties(props);
    }

    public void enviarConAdjunto(String mailTo, byte[] adjunto, String nombreAdjunto)
            throws MailGeneratorException {

        final Properties props = obtenerProperties();
        Session session = obtenerSession(props);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.mail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
            message.setSubject(this.subject);

            MimeBodyPart parteTexto = new MimeBodyPart();
            parteTexto.setText(this.text);

            MimeBodyPart parteAdjunto = new MimeBodyPart();
            DataSource source = new ByteArrayDataSource(adjunto, "image/png");
            parteAdjunto.setDataHandler(new DataHandler(source));
            parteAdjunto.setFileName(nombreAdjunto);

            Multipart partes = new MimeMultipart();
            partes.addBodyPart(parteTexto);
            partes.addBodyPart(parteAdjunto);

            message.setContent(partes);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new MailGeneratorException();
        } catch (Exception e) {
            throw new MailGeneratorException();
        }
    }

    private boolean valido(Properties props) {
        List<String> propiedades = Arrays.asList(MAIL, PASS, HOST,
                PORT, SUBJECT, TEXT);

        return props != null && props.keySet().containsAll(propiedades);
    }

    private Properties obtenerProperties() {
        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.port", this.port);
        props.put(MAIL, this.mail);
        props.put(PASS, this.pass);

        return props;
    }

    private Session obtenerSession(final Properties props) {
        return Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty(MAIL), props.getProperty(PASS));
            }
        });
    }
}
