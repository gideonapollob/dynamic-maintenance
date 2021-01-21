package dev.gideon.maintenance.dynamic;

import dev.gideon.maintenance.dynamic.controller.AuthController;
import dev.gideon.maintenance.dynamic.controller.HomeController;
import dev.gideon.maintenance.dynamic.facade.interfaces.DataFacade;
import dev.gideon.maintenance.dynamic.service.interfaces.PasswordService;
import dev.gideon.maintenance.dynamic.service.interfaces.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class BootstrapTests {
	@Autowired
	private AuthController authController;

	@Autowired
	private HomeController homeController;

	@Autowired
	private DataFacade dataFacade;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordService passwordService;

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
		assertThat(authController).isNotNull();
		assertThat(homeController).isNotNull();
		assertThat(dataFacade).isNotNull();
		assertThat(userDetailsService).isNotNull();
		assertThat(passwordService).isNotNull();
		assertThat(userService).isNotNull();
	}
}
