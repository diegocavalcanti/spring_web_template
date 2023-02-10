package net.dc.demo.controller;

import net.dc.demo.model.UserModel;
import net.dc.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.List;

/**
 * Created by User on 2/10/2023.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/add")
    public String addUser() throws UnknownHostException {
        UserModel model = new UserModel();
        model.setNome("diego");
        userRepository.save(model);
        return "usuario salvo com sucesso";
    }

    @GetMapping("/all")
    public String getUsers() throws UnknownHostException {
        List<UserModel> users = userRepository.findAll();

        StringBuffer sb = new StringBuffer();
        for (UserModel u : users) {
            sb.append(" </br> ");
            sb.append(u.getId() + " - " + u.getNome());
        }

        return "users: "  +sb.toString();
    }
}
