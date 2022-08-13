package com.alireza.sadeghi.authorizationserver.config;


import com.alireza.sadeghi.authorizationserver.model.User;
import com.alireza.sadeghi.authorizationserver.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity security) throws Exception {
        DefaultSecurityFilterChain build = security.authorizeRequests(x -> x.anyRequest().authenticated()).formLogin().and().build();
        log.info(build.toString());
        return build;
    }

    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        try {

            UserDetailsService userDetailsService = username -> {
                log.info(username);
                User byUsername = userRepo.findByUsername(username);
                log.info("user found");
                log.info("user:{}",byUsername);
                return byUsername;
            };
            log.info("UserDetailsService");
            log.info(userDetailsService.toString());
            log.info("ending");
            return userDetailsService;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }


}
