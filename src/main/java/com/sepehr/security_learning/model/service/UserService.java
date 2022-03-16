package com.sepehr.security_learning.model.service;

import com.sepehr.security_learning.model.entity.User;
import com.sepehr.security_learning.model.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveOrUpdate(User user){
        userRepository.save(user);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName)
                .orElseThrow(IllegalArgumentException::new);
    }

    public void deleteUserByUserName(String userName){
        userRepository.delete(findByUserName(userName));
    }

    public boolean userExistsByUserName(String userName){
        return userRepository.existsByUserName(userName);
    }
}
