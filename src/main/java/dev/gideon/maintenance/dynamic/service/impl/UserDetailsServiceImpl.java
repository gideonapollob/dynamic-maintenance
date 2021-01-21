package dev.gideon.maintenance.dynamic.service.impl;

import dev.gideon.maintenance.dynamic.model.StompPrincipal;
import dev.gideon.maintenance.dynamic.model.entity.auth.Password;
import dev.gideon.maintenance.dynamic.model.entity.auth.User;
import dev.gideon.maintenance.dynamic.service.interfaces.PasswordService;
import dev.gideon.maintenance.dynamic.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Objects;

@Slf4j
@Service @Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private MessageSource messageSource;

	@Autowired
    private PasswordService passwordService;

	@Autowired
	private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Locale locale = LocaleContextHolder.getLocale();
        String[] messageParameters = { username };

        String errorMessage = messageSource.getMessage(
                "exception.usernameNotFoundException",
                messageParameters,
                locale);

        User user = userService.findByUsername(username);

        if(Objects.isNull(user)) {
        	log.debug(String.format("User with the username \"%s\" not found.", username));
            throw new UsernameNotFoundException(errorMessage);
        }

		try {
            Password password = passwordService.findLastPasswordByUser(user);

			if(password == null) {
				log.debug(String.format("Password record for user with the username \"\" not found.", username));
				throw new NullPointerException(errorMessage);
			}

			StompPrincipal stompPrincipal = new StompPrincipal();
			stompPrincipal.setId(user.getId());
			stompPrincipal.setPassword(password.getPasswordHash());
			stompPrincipal.setUser(user);

			log.debug(String.format("Preparing stompPrincipal instance for username \"\".", username));
			return stompPrincipal;
		} catch(NullPointerException exception) {
			exception.printStackTrace();
			throw new UsernameNotFoundException(errorMessage);
		}
    }
}