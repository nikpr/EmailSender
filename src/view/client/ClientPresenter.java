package view.client;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jfoenix.controls.JFXTextField;
import domain.Model.Client;
import domain.events.ContactsIsLoadedEvent;
import domain.events.ExceptionHappenedEvent;
import domain.events.SelectClientEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.inject.Inject;

/**
 *
 * @author Nik
 */
public class ClientPresenter implements Initializable {

    @FXML
    private TableView<Client> tableViewContacts;
    @FXML
    private TableColumn<Client, String> tableColumnClientName;
    @FXML
    private TableColumn<Client, String> tableColumnClientEmail;
    @FXML
    private JFXTextField search;
    @Inject
    private EventBus bus;
    private FilteredList<Client> filteredData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bus.register(this);
        search.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    refresh();
                    filteredData.setPredicate(client -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        String objectvalues = client.getEmail();
                        return objectvalues.toLowerCase().contains(lowerCaseFilter);
                    });
                });
        tableViewContacts.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                Client client = tableViewContacts.getSelectionModel().getSelectedItem();
                SelectClientEvent selectionEvent = new SelectClientEvent(client);
                bus.post(selectionEvent);
            }
        });
    }

    @Subscribe
    private void setContactsTable(ContactsIsLoadedEvent event) {
        ObservableList clientsContacts = FXCollections.observableArrayList();
        clientsContacts.addAll(event.getClientContacts());
        tableViewContacts.setItems(clientsContacts);
        tableColumnClientEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableColumnClientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        filteredData = new FilteredList<>(FXCollections.observableArrayList(), p -> true);
    }

    private void refresh() {
            List<Client> list = tableViewContacts.getItems();
            filteredData = new FilteredList<>(FXCollections.observableArrayList(list), p -> true);
            SortedList<Client> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableViewContacts.comparatorProperty());
            tableViewContacts.setItems(sortedData);
        
    }
}
