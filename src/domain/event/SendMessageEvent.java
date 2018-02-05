package domain.events;

import domain.Model.EmailBody;

/**
 *
 * @author Nik
 */
public class SendMessageEvent {

    private EmailBody emailBody;

    public SendMessageEvent(EmailBody emailBody) {
        this.emailBody = emailBody;
    }

    public EmailBody getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(EmailBody emailBody) {
        this.emailBody = emailBody;
    }

}
