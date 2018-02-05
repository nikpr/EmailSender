package view.dialog;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
/**
 *
 * @author Nik
 */
public class NotificationBuider {
    
    public  static Notifications buildNotification(String title, String message ){
    Notifications notification = Notifications.create()
            .title(title)
            .text(message)
            .hideAfter(Duration.seconds(3));
    return notification;
    }
}
