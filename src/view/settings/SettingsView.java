package view.settings;

import com.airhacks.afterburner.views.FXMLView;

/**
 *
 * @author Nik
 */
public class SettingsView extends FXMLView {
    
    public SettingsPresenter getRealPresenter() {
        return (SettingsPresenter) super.getPresenter();
    }
    
}
