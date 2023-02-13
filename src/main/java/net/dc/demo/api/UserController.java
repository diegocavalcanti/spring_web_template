package net.dc.demo.api;

import net.dc.demo.model.UserModel;
import net.dc.demo.service.UserService;
import net.dc.demo.views.AbstractView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by User on 2/10/2023.
 */

@RestController
@RequestMapping("/user")
public class UserController extends AbstractView {

    @Autowired
    UserService userService;

    @GetMapping("/add")
    public String addUser() throws UnknownHostException {
        UserModel model = new UserModel();
        model.setNome("diego");
        userService.addUser(model);
        return "usuario salvo com sucesso";
    }

    @GetMapping("/all")
    public List<UserModel> getUsers() throws UnknownHostException {
        return  userService.getAll();

    }


}
