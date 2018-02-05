package app;

import com.airhacks.afterburner.injection.Injector;
import com.google.common.eventbus.EventBus;
import view.dialog.DialogManager;
import view.dialog.FileChooserCreator;
import domain.EmailCourier.EmailCourier;
import domain.Parsers.HtmlTemplateManager;
import domain.Properties.InFileSettingsStorage;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.root.RootView;

/**
 *
 * @author Nik
 */
public class EmailSender extends Application {
    
    @Override
    public void start(Stage primaryStage) {
           
        Map<Object, Object> context = new HashMap<>();
        context.put("settingManager", new InFileSettingsStorage());
        context.put("htmlManager", new HtmlTemplateManager());
        context.put("dialogManager", new DialogManager());
        context.put("fileLoader",new FileChooserCreator());
        context.put("bus", new EventBus("emailSenderBus"));
        context.put("settingStorage", new InFileSettingsStorage());
        context.put("courier", new EmailCourier());
        Injector.setConfigurationSource(context::get);
        RootView view = new RootView();
        Scene scene = new Scene(view.getView());
        primaryStage.setTitle("EmailSender");
        primaryStage.setScene(scene);
        primaryStage.show();
         
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
