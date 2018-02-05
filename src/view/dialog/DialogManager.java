package view.dialog;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Nik
 */
public class DialogManager {
    
    public Stage createDialog(DialogFiles file) throws IOException {
     Stage dialog = new Stage();
     dialog.initModality(Modality.NONE);
     String scenePath = file.getDialogFilePath();
     FXMLLoader loader = new FXMLLoader(getClass().getResource(scenePath));
     Parent root  = loader.load();
     Scene scene = new Scene(root);
     dialog.setScene(scene);
     dialog.setTitle(file.getTitle());
        return dialog;
    }
    
}
