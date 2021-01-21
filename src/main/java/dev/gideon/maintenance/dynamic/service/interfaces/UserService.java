package dev.gideon.maintenance.dynamic.service.interfaces;

import dev.gideon.maintenance.dynamic.model.entity.auth.User;

public interface UserService extends EntityService<User> {
    User findByUsername(String username);
}
