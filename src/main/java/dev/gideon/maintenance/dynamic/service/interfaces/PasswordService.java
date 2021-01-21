package dev.gideon.maintenance.dynamic.service.interfaces;

import dev.gideon.maintenance.dynamic.model.entity.auth.Password;
import dev.gideon.maintenance.dynamic.model.entity.auth.User;

public interface PasswordService extends EntityService<Password> {
    Password findLastPasswordByUser(User user);
}
