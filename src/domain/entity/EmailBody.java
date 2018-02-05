package domain.Model;

import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nik
 */
public class EmailBody implements Serializable {

    private String recipientEmailAddress;
    private String subject;
    private String emailText;
    private List<File> attachments;
    private File htmlTemplateFile;

    public EmailBody() {
        attachments = new LinkedList<>();
    }

    public EmailBody(String recipientEmailAddress, String subject, String emailText) {
        this();
        this.recipientEmailAddress = recipientEmailAddress;
        this.subject = subject;
        this.emailText = emailText;
        attachments = new LinkedList<>();
    }

    public String getRecipientEmailAddress() {
        return recipientEmailAddress;
    }

    public void setRecipientEmailAddress(String recipientEmailAddress) {
        this.recipientEmailAddress = recipientEmailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }

    public List<File> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<File> attachments) {
        this.attachments = attachments;
    }

    public void append(File attachment) throws Exception {
        if (attachment.length() > 2.5e+7) {
           throw  new Exception("File if greater then 25 mb");
        }
        attachments.add(attachment);
    }

    public File getHtmlTemplateFile() {
        return htmlTemplateFile;
    }

    public void setHtmlTemplateFile(File htmlTemplateFile) {
        this.htmlTemplateFile = htmlTemplateFile;
    }

}
