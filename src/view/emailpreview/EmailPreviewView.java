/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.emailpreview;

import com.airhacks.afterburner.views.FXMLView;

/**
 *
 * @author Nik
 */
public class EmailPreviewView extends FXMLView {
    
    public EmailPreviewPresenter getRealPresenter() {
        return (EmailPreviewPresenter) super.getPresenter();
    }
    
}
