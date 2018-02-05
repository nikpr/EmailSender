/**
 *
 * @author Nik
 */
package domain.EmailCourier;

import domain.Model.EmailBody;
import domain.Properties.InFileSettingsStorage;
import java.util.Properties;
import domain.Properties.SettingsStorage;
import java.io.File;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.mail.EmailException;

public class EmailCourier {
    
    @Inject
    private final SettingsStorage settingStorage;
    private Session session;
    private Message message;
    private final Multipart multipart;
    
    public EmailCourier() {
        multipart = new MimeMultipart();
        settingStorage = new InFileSettingsStorage();
    }
    
    public void send(EmailBody body) throws Exception {
        if (!body.getRecipientEmailAddress().isEmpty()) {
            setSession();
            setMessageFields(body);
            Transport.send(message);
        } else {
            throw new Exception("Please specifiy recipient!");
        }
    }
    
    private void setSession() {
        Properties serverProperties = settingStorage.load();
        String email = serverProperties.getProperty("email");
        String password = serverProperties.getProperty("password");
        session = Session.getInstance(serverProperties,
                new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });
    }
    
    private void setMessageFields(EmailBody body) throws EmailException, MessagingException {
        message = new MimeMessage(session);
        message.addHeader("Precedence", "bulk");
        message.setFrom(new InternetAddress(body.getRecipientEmailAddress()));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(body.getRecipientEmailAddress()));
        message.setSubject(body.getSubject());
        String htmlText = body.getEmailText();
        createTextPart(htmlText);
        for (File atachmentFile : body.getAttachments()) {
            createAttachment(atachmentFile);
        }
        message.setContent(multipart);
    }
    
    private void createTextPart(String htmlText) throws MessagingException {
        BodyPart textPart = new MimeBodyPart();
        textPart.setContent(htmlText, "text/html; charset=UTF-8");
        multipart.addBodyPart(textPart);
    }
    
    private void createAttachment(File file) throws MessagingException {
        BodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(file);
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(file.getName());
        multipart.addBodyPart(attachmentBodyPart);
    }
    
}
