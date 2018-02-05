package view.dialog;

/**
 *
 * @author Nik
 */
public enum DialogFiles {
    SERVER_SETTINGS {
        @Override
        public String getDialogFilePath() {
            return "/view/settings/settings.fxml";
        }

        @Override
        public String getTitle() {
            return "Server Settings";
        }
    };
   
    public abstract String getDialogFilePath();
    public abstract String getTitle();
}
