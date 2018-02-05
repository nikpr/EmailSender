package view.settings;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import domain.Properties.InFileSettingsStorage;
import domain.Properties.SettingsStorage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.inject.Inject;

/**
 *
 * @author Nik
 */
public class SettingsPresenter implements Initializable {

    @FXML
    private JFXTextField hostName;
    @FXML
    private JFXToggleButton isTLSEnabled;
    @FXML
    private JFXTextField userName;
    @FXML
    private JFXPasswordField userPassword;
    @FXML
    private JFXToggleButton isAuthentication;
    @FXML
    private Label statusLabel;
    @Inject
    private SettingsStorage settingManager;
    @FXML
    private JFXTextField port;

    private Properties properties;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settingManager = new InFileSettingsStorage();
        setFields();
    }

    @FXML
    private void onSaveButtonClick(ActionEvent event) {
        try {
            properties = new Properties();
            properties.setProperty("email", userName.getText());
            properties.setProperty("password", userPassword.getText());
            properties.setProperty("mail.smtp.host", hostName.getText());
            properties.setProperty("mail.smtp.port", port.getText());
            properties.setProperty("mail.smtp.auth", Boolean.toString(isAuthentication.isSelected()));
            properties.setProperty("mail.smtp.starttls.enable", Boolean.toString(isTLSEnabled.isSelected()));
            settingManager.save(properties);
            statusLabel.setText("Saved");
        } catch (IOException ex) {
            statusLabel.setText(ex.getMessage());
        }
    }

    private void setFields() {
        properties = settingManager.load();
        if (properties == null) {
            statusLabel.setText("Properties is Empty");
            return;
        }
        try {
            userName.setText(properties.getProperty("email"));
            userPassword.setText(properties.getProperty("password"));
            port.setText(properties.getProperty("mail.smtp.port"));
            hostName.setText(properties.getProperty("mail.smtp.host"));
            boolean isTLS = Boolean.valueOf(properties.getProperty("mail.smtp.starttls.enable"));
            if (isTLS) {
                isTLSEnabled.setSelected(true);
            }
            boolean isAuthRequired = Boolean.valueOf(properties.getProperty("mail.smtp.auth"));
            if (isAuthRequired) {
                isAuthentication.setSelected(true);
            }
        } catch (NullPointerException exception) {
                statusLabel.setText("Some field is empty");
        }
    }

}
