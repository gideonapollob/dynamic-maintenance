package dev.gideon.maintenance.dynamic.service.impl;

import com.sun.istack.NotNull;
import dev.gideon.maintenance.dynamic.model.entity.auth.Password;
import dev.gideon.maintenance.dynamic.model.entity.auth.User;
import dev.gideon.maintenance.dynamic.repository.interfaces.PasswordRepository;
import dev.gideon.maintenance.dynamic.service.interfaces.PasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class PasswordServiceImpl
        extends EntityServiceImpl<Password, PasswordRepository>
        implements PasswordService {

    @Override
    public Password findLastPasswordByUser(@NotNull User user) {
        return repository.findTopByPasswordIdUserOrderByPasswordIdUpdatedDateDesc(user);
    }
}
