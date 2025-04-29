package org.frompast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;

@SpringBootApplication
@EnableLdapRepositories
@ConfigurationPropertiesScan
public class FromPastApp {
    public static void main(String[] args) {
        SpringApplication.run(FromPastApp.class, args);
    }
}
