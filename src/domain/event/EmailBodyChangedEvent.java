package domain.events;

import domain.Model.EmailBody;

/**
 *
 * @author Nik
 */
public class EmailBodyChangedEvent {

    private EmailBody emailBody = new EmailBody();

    public EmailBodyChangedEvent(EmailBody emailBody) {
        this.emailBody = emailBody;
    }

    public EmailBody getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(EmailBody emailBody) {
        this.emailBody = emailBody;
    }

}
