package view.emailpreview;

import com.google.common.eventbus.EventBus;
import domain.Model.EmailBody;
import domain.Parsers.HtmlParser;
import domain.events.EmailBodyChangedEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import javax.inject.Inject;

/**
 *
 * @author Nik
 */
public class EmailPreviewPresenter implements Initializable {

    @FXML
    private SplitPane previewSplitPane;
    @FXML
    private WebView webPreview;
    @FXML
    private Label status;
    @FXML
    private TextArea htmlPreviewArea;
    @Inject
    private EventBus bus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        htmlPreviewArea.textProperty().addListener((observable, newValue, oldValue) -> {
            webPreview.getEngine().loadContent(oldValue);
            EmailBody emailBody = new EmailBody();
            emailBody.setEmailText(htmlPreviewArea.getText());
            EmailBodyChangedEvent events = new EmailBodyChangedEvent(emailBody);
            bus.post(events);
        });
    }

    @FXML
    private void onCloseHtmlCodeButtonClick(ActionEvent event) {
        previewSplitPane.setDividerPosition(0, 1);
    }

    @FXML
    private void onSetDivederToMiddleButtonClick(ActionEvent event) {
        previewSplitPane.setDividerPosition(0, 0.5);

    }

    @FXML
    private void onCloseWebPreviewbuttonClick(ActionEvent event) {
        previewSplitPane.setDividerPosition(0, 0);

    }

    public void setHtmlText(String text) {
        HtmlParser parser = new HtmlParser();
        String alignedText = parser.alignText(text);
        htmlPreviewArea.setText(alignedText);
        webPreview.getEngine().loadContent(alignedText);
    }

    private void setStatus(String text) {
        status.setText(text);
    }
}
