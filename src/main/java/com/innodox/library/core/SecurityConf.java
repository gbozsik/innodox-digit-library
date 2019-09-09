package com.innodox.library.core;

import com.innodox.library.entity.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.xml.ws.WebServiceException;


/**
 * SPRING SECURITY CONFIGURÁLÁS
 */


@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {


    @Autowired
    DetailService detailService;

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(detailService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailService)
                .passwordEncoder(Password.PASSWORD_ENCODER);
        auth.authenticationProvider(customAuthenticationProvider);
//        auth.inMemoryAuthentication()
//                .withUser("memuser")
//                .password(bCryptPasswordEncoder().encode("pass"))
//                .roles("USER");
    }

    //
//
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable().authorizeRequests()
//        httpSecurity.authorizeRequests()
                .antMatchers("/api/getactualuser").permitAll()
                .antMatchers("/api/loggingout").permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
//                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .httpBasic().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        httpSecurity.headers().frameOptions().disable();
    }

}
