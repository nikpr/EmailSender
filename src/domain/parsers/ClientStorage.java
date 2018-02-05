package domain.Parsers;

import domain.Model.Client;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Nik
 */
public interface ClientStorage {
    
      List<Client> loadClients() throws IOException;
     
}
