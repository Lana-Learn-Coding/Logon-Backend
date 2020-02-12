package com.lana.logon;


import com.lana.logon.security.jwt.JwtProperties;
import com.lana.logon.service.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableConfigurationProperties({StorageProperties.class, JwtProperties.class})
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class LogonApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogonApplication.class, args);
    }

}
