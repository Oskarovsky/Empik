package com.oskarro.empik.config;

import com.oskarro.empik.gateway.GithubGateway;
import com.oskarro.empik.service.UserService;
import com.oskarro.empik.service.UserServiceBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Properties;

@TestConfiguration
public class EmpikTestConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        Properties properties = new Properties();
        properties.setProperty("github.url", "https://api.github.com/users/");
        pspc.setProperties(properties);
        return pspc;
    }

    @Bean
    public GithubGateway githubGateway() {
        return new GithubGateway();
    }

    @Bean
    public UserService userService() {
        return new UserServiceBean(githubGateway());
    }
}
