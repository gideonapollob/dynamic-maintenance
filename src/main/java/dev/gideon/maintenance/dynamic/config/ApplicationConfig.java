package dev.gideon.maintenance.dynamic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ApplicationConfig {
    @Bean
    public AbstractResourceBasedMessageSource messageSource() {
        AbstractResourceBasedMessageSource source =
                new ResourceBundleMessageSource();

        source.setBasenames("i18n/messages");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
}
