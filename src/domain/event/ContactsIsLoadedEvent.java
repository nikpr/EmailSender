package domain.events;

import domain.Model.Client;
import java.util.List;

/**
 *
 * @author Nik
 */
public class ContactsIsLoadedEvent {

    private List<Client> clientContacts;

    public ContactsIsLoadedEvent(List<Client> clientContacts) {
        this.clientContacts = clientContacts;
    }

    public List<Client> getClientContacts() {
        return clientContacts;
    }

    public void setClientContacts(List<Client> clientContacts) {
        this.clientContacts = clientContacts;
    }

}
