package view.status;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import domain.events.ExceptionHappenedEvent;
import domain.events.StatusChangedEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.inject.Inject;

/**
 *
 * @author Nik
 */
public class StatusPresenter implements Initializable {

    @FXML
    private Label statusLabel;
    @Inject
    private EventBus bus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bus.register(this);
        statusLabel.setTextFill(StatusColors.Success.getColor());
    }

    @Subscribe
    public void responseOn(StatusChangedEvent event) {
        statusLabel.setTextFill(StatusColors.Success.getColor());
        statusLabel.setText(event.getStatusText());
    }

    @Subscribe
    public void responseOn(ExceptionHappenedEvent event) {
        Exception exception = event.getException();
        statusLabel.setTextFill(StatusColors.Error.getColor());
        statusLabel.setText(exception.getMessage());
    }
}
