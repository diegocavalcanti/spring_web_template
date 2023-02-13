package net.dc.demo.service;

import net.dc.demo.model.UserModel;
import net.dc.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by diego.daniel on 13/02/2023.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserModel> getAll() {
        return userRepository.findAll();

    }


    public void addUser(UserModel model) {
        userRepository.save(model);
    }
}
