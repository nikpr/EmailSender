package view.emailbody;

import com.airhacks.afterburner.views.FXMLView;

/**
 *
 * @author Nik
 */
public class EmailBodyView extends FXMLView {
    
    public EmailBodyPresenter getRealPresenter() {
        return (EmailBodyPresenter) super.getPresenter();
    }
    
}
