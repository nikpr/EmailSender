package domain.Properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author Nik
 */
public class InFileSettingsStorage implements SettingsStorage {

    public InFileSettingsStorage() {
    }

    @Override
    public Properties load() {
        Properties property = new Properties();
        try (FileInputStream stream = new FileInputStream(ConfigurationFiles.SERVER_SETTINGS.getUrl())) {
            property.load(stream);
        } catch (IOException ex) {
        }
        return property;
    }

    @Override
    public void save(Properties properties) throws IOException  {
        FileOutputStream writer;
        writer = new FileOutputStream(ConfigurationFiles.SERVER_SETTINGS.getUrl());       
        properties.store(writer, new Date().toString());
    }

}
