package dev.gideon.maintenance.dynamic.facade.impl;

import dev.gideon.maintenance.dynamic.facade.interfaces.DataFacade;
import dev.gideon.maintenance.dynamic.model.entity.auth.Password;
import dev.gideon.maintenance.dynamic.model.entity.auth.PasswordId;
import dev.gideon.maintenance.dynamic.model.entity.auth.User;
import dev.gideon.maintenance.dynamic.service.interfaces.PasswordService;
import dev.gideon.maintenance.dynamic.service.interfaces.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Objects;

@Log4j2
@Service @Transactional
public class DataFacadeImpl implements DataFacade {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PasswordService passwordService;

    @Value("${dev.gideon.maintenance.dynamic.model.entity.auth.superuser}")
    private String SUPERUSER;

    @Value("${dev.gideon.maintenance.dynamic.model.entity.auth.superuser.password}")
    private String SUPERUSER_PASSWORD;

    @Autowired
    private UserService userService;

    @Override
    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        initializeData();
    }

    private void initializeData() {
        User user = userService.findByUsername(SUPERUSER);

        if(Objects.isNull(user)) {
            throw new UsernameNotFoundException(
                    String.format("Superuser not found with the username \"%s\".",
                            SUPERUSER));
        }

        Password password = passwordService.findLastPasswordByUser(user);

        if(Objects.isNull(password)) {
            log.info(String.format(
                    "Superuser password not found, creating with the password \"%s\".",
                    SUPERUSER_PASSWORD));

            PasswordId passwordId = new PasswordId(user, ZonedDateTime.now());
            password = new Password();
            password.setPasswordId(passwordId);
            password.setPasswordHash(bCryptPasswordEncoder.encode(SUPERUSER_PASSWORD));
            password.setUpdatedBy(user);
            passwordService.save(password);
        }
    }
}
