package view.emailbody;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jfoenix.controls.JFXTextField;
import domain.EmailCourier.EmailCourier;
import domain.Model.EmailBody;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.HTMLEditor;
import domain.events.EmailBodyChangedEvent;
import domain.events.ExceptionHappenedEvent;
import domain.events.SelectClientEvent;
import javax.inject.Inject;

/**
 *
 * @author Nik
 */
public class EmailBodyPresenter implements Initializable {
    
    @FXML
    private HTMLEditor htmlTextEditor;
    @FXML
    private JFXTextField subjectTextField;
    @FXML
    private JFXTextField recipientTextField;
    @Inject
    private EventBus bus;
    @Inject    
    private EmailCourier courier;
    private EmailBody emailBody;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailBody = new EmailBody();
        bus.register(this);
    }
    
    @FXML
    private void onSendButtonClick(ActionEvent event) throws Exception {
        setEmailBody();
        try {
            courier.send(emailBody);
            System.out.println("Sent!");
        } catch (Exception ex) {
            ExceptionHappenedEvent excepetionHappened = new ExceptionHappenedEvent(ex);
            bus.post(excepetionHappened);
        }
    }
    
    @Subscribe
    private void responseOn(EmailBodyChangedEvent event) {
        emailBody = event.getEmailBody();
        htmlTextEditor.setHtmlText(emailBody.getEmailText());
        recipientTextField.setText(emailBody.getRecipientEmailAddress());
        subjectTextField.setText(emailBody.getSubject());
    }
    
    @Subscribe
    private void responseOn(SelectClientEvent event) {
        String newRecipientAddress = event.getClient().getEmail();
        recipientTextField.setText(newRecipientAddress);
    }
    
    public void setHtmlEditorText(String htmlText) {
        htmlTextEditor.setHtmlText(htmlText);
    }
    
    public EmailBody getEmailBody() {
        setEmailBody();
        return emailBody;
    }
    
    private void setEmailBody() {
        emailBody.setEmailText(htmlTextEditor.getHtmlText());
        emailBody.setSubject(subjectTextField.getText());
        emailBody.setRecipientEmailAddress(recipientTextField.getText());
    }
    
}
