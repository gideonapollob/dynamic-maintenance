package dev.gideon.maintenance.dynamic.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void unauthenticatedAccess() throws Exception {
        mockMvc.perform(get("/"))
                // Gideon: This is replaced by the full path since Spring
                // Security uses the full path in redirection.
                // .andExpect(redirectedUrl("/auth/login"))
                .andExpect(redirectedUrl("http://localhost/auth/login"));
    }

    @Test
    void invalidLoginCredentials() throws Exception {
        RequestBuilder requestBuilder = formLogin().user("").password("");

        mockMvc.perform(requestBuilder)
                // Gideon: This is replaced by the full path since Spring
                // Security uses the full path in redirection.
                // .andExpect(redirectedUrl("/auth/login"))
                .andExpect(redirectedUrl("http://localhost/auth/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void validLoginCredentials() throws Exception {
        RequestBuilder requestBuilder =
                formLogin().user("administrator").password("password");

        mockMvc.perform(requestBuilder)
                .andExpect(redirectedUrl("http://localhost/auth/login"))
                .andExpect(status().is3xxRedirection());
    }
}
