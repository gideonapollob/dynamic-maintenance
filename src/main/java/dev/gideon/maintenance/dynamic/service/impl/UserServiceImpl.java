package dev.gideon.maintenance.dynamic.service.impl;

import com.sun.istack.NotNull;
import dev.gideon.maintenance.dynamic.model.entity.auth.User;
import dev.gideon.maintenance.dynamic.repository.interfaces.UserRepository;
import dev.gideon.maintenance.dynamic.service.interfaces.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class UserServiceImpl
        extends EntityServiceImpl<User, UserRepository>
        implements UserService {

    @Override
    public User findByUsername(@NotNull String username) {
        return repository.findByUsername(username);
    }
}
