package com.rs2.note.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Order(4)
public class APISecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationService authenticationService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
                .antMatcher("/api/**/**")
                .authorizeRequests()
                .antMatchers(
                        "/api/authenticate/authenticateByPin",
                        "/api/phoneNumberAdapter/validate**/**",
                        "/api/users/isValidPasswordFormat",
                        "/api/dev/smssync**/**",
                        "/api/ussd**/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(apiAuthenticationFailureHandler())
                .and()
                .authenticationProvider(new AuthenticationProviderImpl(SystemChannelTypeEnum.REST, authenticationService, CredentialsTypeEnum.PIN))
                .exceptionHandling().authenticationEntryPoint(apiAuthenticationFailureHandler())
                .and()
                .headers()
                .cacheControl().disable()
                .frameOptions().disable();
    }

    private static AuthenticationEntryPoint apiAuthenticationFailureHandler() {
        return new APIAuthenticationFailureHandler();
    }

}

