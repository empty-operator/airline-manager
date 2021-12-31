package org.leniv.airlinemanager.service.impl;

import lombok.AllArgsConstructor;
import org.leniv.airlinemanager.entity.User;
import org.leniv.airlinemanager.repository.UserRepository;
import org.leniv.airlinemanager.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    @Override
    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
    }

}
