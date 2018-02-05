package view.client;

import com.airhacks.afterburner.views.FXMLView;

/**
 *
 * @author Nik
 */
public class ClientView extends FXMLView {
    
    public ClientPresenter getRealPresenter() {
        return (ClientPresenter) super.getPresenter();
    }
    
}
