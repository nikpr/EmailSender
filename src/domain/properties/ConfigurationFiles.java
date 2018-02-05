package domain.Properties;

/**
 *
 * @author Nik
 */
public enum ConfigurationFiles {
  SERVER_SETTINGS("config\\server.ini");

   private final String url;
 
   private ConfigurationFiles(String envUrl) {
        this.url = envUrl;
    }
 
    public String getUrl() {
        return url;
    }
}
