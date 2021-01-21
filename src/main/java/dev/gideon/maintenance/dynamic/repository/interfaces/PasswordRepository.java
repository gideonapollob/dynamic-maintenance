package dev.gideon.maintenance.dynamic.repository.interfaces;

import dev.gideon.maintenance.dynamic.model.entity.auth.Password;
import dev.gideon.maintenance.dynamic.model.entity.auth.PasswordId;
import dev.gideon.maintenance.dynamic.model.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, PasswordId> {
    Password findTopByPasswordIdUserOrderByPasswordIdUpdatedDateDesc(User user);
}
