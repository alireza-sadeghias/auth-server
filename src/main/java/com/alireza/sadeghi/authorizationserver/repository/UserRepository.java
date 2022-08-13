package com.alireza.sadeghi.authorizationserver.repository;

import com.alireza.sadeghi.authorizationserver.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);

}
