package view.root;

import view.client.ClientView;
import com.google.common.eventbus.EventBus;
import view.dialog.DialogFiles;
import view.dialog.DialogManager;
import view.dialog.FileChooserCreator;
import domain.Model.Client;
import domain.Model.EmailBody;
import domain.Parsers.ClientFileParser;
import domain.Parsers.HtmlTemplateManager;
import domain.events.ContactsIsLoadedEvent;
import domain.events.EmailBodyChangedEvent;
import domain.events.StatusChangedEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.inject.Inject;
import view.emailpreview.EmailPreviewView;
import view.emailbody.EmailBodyView;
import view.status.StatusView;

/**
 *
 * @author Nik
 */
public class RootPresenter implements Initializable {

    private EmailBodyView emailBodyView;
    private ClientView contactView;
    private StatusView statusView;
    private EmailPreviewView previewView;
    @Inject
    private HtmlTemplateManager htmlManager;
    @Inject
    private DialogManager dialogManager;
    @Inject
    private FileChooserCreator fileLoader;
    @Inject
    private ClientFileParser clientsFileParser;
    private FileChooser.ExtensionFilter filter;
    @Inject
    private EventBus bus;
    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientsFileParser = new ClientFileParser();
        loadScenes();

        borderPane.setBottom(statusView.getView());
        borderPane.setLeft(contactView.getView());
        borderPane.setCenter(emailBodyView.getView());
        BorderPane.setAlignment(statusView.getView(), Pos.CENTER);
        BorderPane.setAlignment(contactView.getView(), Pos.CENTER);
        BorderPane.setAlignment(emailBodyView.getView(), Pos.CENTER);
    }

    private void loadScenes() {
        statusView = new StatusView();
        emailBodyView = new EmailBodyView();
        contactView = new ClientView();
    }

    @FXML
    private void onOpenMenuButtonClick(ActionEvent event) {
        filter = new FileChooser.ExtensionFilter("XML", "*.xml");
        fileLoader = new FileChooserCreator(filter);
        File file = fileLoader.chooseFile(new Stage());
        try {
            EmailBody emailBody = htmlManager.load(file);
            EmailBodyChangedEvent emailBodyChangedEvent = new EmailBodyChangedEvent(emailBody);
            bus.post(emailBodyChangedEvent);
        } catch (Exception ex) {
            notifyStatusView(ex.getMessage(), true);
        }
    }

    @FXML
    private void onSaveAsMenuButtonClick(ActionEvent event) {
        filter = new FileChooser.ExtensionFilter("XML", "*.xml");
        fileLoader = new FileChooserCreator(filter);
        File file = fileLoader.chooseFile(new Stage());
        try {
            EmailBody emailBody = emailBodyView.getRealPresenter().getEmailBody();
            htmlManager.save(file, emailBody);
        } catch (Exception ex) {
            notifyStatusView(ex.getMessage(), true);
        }
    }

    @FXML
    private void onLoadContactsFromFileMenuButtonClick(ActionEvent event) {
        filter = new FileChooser.ExtensionFilter("Excel", "*.xls" );
        fileLoader = new FileChooserCreator(filter);
        File file = fileLoader.chooseFile(new Stage());
        try {
            ClientFileParser.file = file;
            List<Client> clientsContacts = clientsFileParser.loadClients();
            ContactsIsLoadedEvent loadedContactsEvent = new ContactsIsLoadedEvent(clientsContacts);
            bus.post(loadedContactsEvent);
        } catch (IOException ex) {
            notifyStatusView(ex.getMessage(), true);
        }
    }

    @FXML
    private void onExitMenuButtonClick(ActionEvent event) {

    }

    @FXML
    private void onAddAttachmentMenuButtonClick(ActionEvent event) {
        fileLoader = new FileChooserCreator();
        File file = fileLoader.chooseFile(new Stage());
       try{
        emailBodyView.getRealPresenter().getEmailBody().append(file);
       } catch(Exception exception){
           notifyStatusView(exception.getMessage(), true);
       }
        notifyStatusView("Attached: "+file.getName(), false);
    }

    @FXML
    private void onServerSettingsMenuButtonClick(ActionEvent event) {
        try {
            Stage stage = dialogManager.createDialog(DialogFiles.SERVER_SETTINGS);
            stage.show();
        } catch (IOException ex) {
            notifyStatusView(ex.getMessage(), true);
        }
    }

    @FXML
    private void onShowInWindowMenuButtonClick(ActionEvent event) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        previewView = new EmailPreviewView();
        Scene scene = new Scene(previewView.getView());
        dialog.setScene(scene);
        EmailBody body = emailBodyView.getRealPresenter().getEmailBody();
        previewView.getRealPresenter().setHtmlText(body.getEmailText());
        dialog.showAndWait();
    }

    @FXML
    private void onAboutMenuButtonClick(ActionEvent event) {
       
    }

    @FXML
    private void onSendTemplateMenuButtonClick(ActionEvent event) {
        filter = new FileChooser.ExtensionFilter("html template", "*.htm", "*.html");
        fileLoader = new FileChooserCreator(filter);
        File file = fileLoader.chooseFile(new Stage());
        try {
            String htmlTemplate = htmlManager.extractHtmlTemplate(file);
            EmailBody emailBody = new EmailBody();
            emailBody.setEmailText(htmlTemplate);
            EmailBodyChangedEvent emailBodyChangedEvent = new EmailBodyChangedEvent(emailBody);
            bus.post(emailBodyChangedEvent);
        } catch (FileNotFoundException ex) {
            notifyStatusView(ex.getMessage(), true);
        }
    }

    private void notifyStatusView(String text, boolean isError) {
        StatusChangedEvent statusChangedEvent = new StatusChangedEvent(text, isError);
        bus.post(statusChangedEvent);
    }

}
