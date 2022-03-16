package com.sepehr.security_learning.security.user;

import com.sepehr.security_learning.model.entity.User;
import com.sepehr.security_learning.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsSecurityManager implements UserDetailsManager {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDetails user) {
        User savingUser = ((UserSecurityDetails) user).getUser();
        userService.saveOrUpdate(savingUser.toBuilder().password(passwordEncoder.encode(savingUser.getPassword())).build());
    }

    @Override
    public void updateUser(UserDetails user) {
        createUser(user);
    }

    @Override
    public void deleteUser(String username) {
        userService.deleteUserByUserName(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // NOOP
    }

    @Override
    public boolean userExists(String username) {
        return userService.userExistsByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserSecurityDetails(userService.findByUserName(username));
    }
}
