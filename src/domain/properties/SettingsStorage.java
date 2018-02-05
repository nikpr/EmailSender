package domain.Properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Nik
 */
public interface SettingsStorage {
     Properties load();
     void save(Properties properties)throws FileNotFoundException, IOException;
}
