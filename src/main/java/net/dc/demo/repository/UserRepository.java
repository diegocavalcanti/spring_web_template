package net.dc.demo.repository;

import net.dc.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by diego.cavalcanti on 2/10/2023.
 */
public interface  UserRepository extends JpaRepository<UserModel, Integer> {
}
