package net.dc.demo.views;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by diego.daniel on 13/02/2023.
 */

@Named
@SessionScoped
public class TemplateView extends AbstractView implements Serializable {

    public void save() {
        addInfo("Success", "Data saved");
    }

    public void update() {
        addWarn("Warning", "Data updated");
    }

    public void delete() {
        addError("Error", "Data deleted");
    }


}
