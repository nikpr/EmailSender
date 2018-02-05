package domain.Parsers;

import domain.Model.Client;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Nik
 */
public class ClientFileParser implements ClientStorage{

   public static File file;

   @Override
    public List<Client> loadClients() throws IOException {
        if (file == null) {
            return null;
        }
        List<Client> clientsContacts = new LinkedList<>();
        FileInputStream fileReader = new FileInputStream(file);
        HSSFWorkbook contactsBook = new HSSFWorkbook(fileReader);
        Sheet contactsSheet = contactsBook.getSheetAt(0);
        Iterator<Row> rowIteator = contactsSheet.iterator();
        while (rowIteator.hasNext()) {
            Row row = rowIteator.next();
            Client clientContact = new Client();
            clientContact.setName(row.getCell(0).toString());
            clientContact.setEmail(row.getCell(1).toString());
            clientsContacts.add(clientContact);
        }
        return clientsContacts;
    }

}
