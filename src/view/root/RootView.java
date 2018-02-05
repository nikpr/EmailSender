package view.root;

import com.airhacks.afterburner.views.FXMLView;

/**
 *
 * @author Nik
 */
public class RootView extends FXMLView {
    
    public RootPresenter getRealPresenter() {
        return (RootPresenter) super.getPresenter();
    }
    
}
