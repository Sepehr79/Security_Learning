package com.sepehr.security_learning.model.repo;


import com.sepehr.security_learning.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    public boolean existsByUserName(String userName);

}
