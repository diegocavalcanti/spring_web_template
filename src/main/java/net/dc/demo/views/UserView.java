package net.dc.demo.views;

import net.dc.demo.model.UserModel;
import net.dc.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

/**
 * Created by diego.daniel on 13/02/2023.
 */

@Named
@ViewScoped
public class UserView extends  AbstractView {

    @Autowired
    UserService userService;


    private UserModel user;

    private String filter;


    @PostConstruct
    public void init() {
        System.out.println("Begin Create Page");
    }





    public List<UserModel> getAll() {
        return userService.getAll();
    }



    public UserModel getUser() {
        return user;
    }


    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String actionRedirectFormPage(){

        System.out.println("Redirect to User Edit Page");
        return "users_form?faces-redirect=true";


    }


    public void actionUpdate() {


    }

}
