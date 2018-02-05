package domain.events;

import domain.Model.Client;

/**
 *
 * @author Nik
 */
public class SelectClientEvent {

    private Client client;

    public SelectClientEvent(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
