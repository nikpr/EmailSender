package view.dialog;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Nik
 */
public class FileChooserCreator {

    private FileChooser fileChooser;

    public FileChooserCreator() {
        fileChooser = new FileChooser();
    }

    public FileChooserCreator(final FileChooser.ExtensionFilter filter) {
        this();
        fileChooser.getExtensionFilters().add(filter);
    }

    public File chooseFile(Stage stage) {
        File file = fileChooser.showOpenDialog(stage);
        return file;
    }

}
