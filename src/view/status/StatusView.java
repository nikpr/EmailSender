package view.status;

import com.airhacks.afterburner.views.FXMLView;

/**
 *
 * @author Nik
 */
public class StatusView extends FXMLView {
    
    public StatusPresenter getRealPresenter() {
        return (StatusPresenter) super.getPresenter();
    }
    
}
