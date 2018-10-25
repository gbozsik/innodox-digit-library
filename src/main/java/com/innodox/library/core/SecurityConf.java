package com.innodox.library.core;

import com.innodox.library.entity.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.xml.ws.WebServiceException;

@EnableGlobalMethodSecurity(securedEnabled = true) //Mit milyen jogu user érhet el
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {



    @Autowired
    DetailService detailService;

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
        auth.userDetailsService(detailService).passwordEncoder(Password.PASSWORD_ENCODER);
    }

    //
//
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.cors().and().csrf().disable().authorizeRequests()
        httpSecurity.authorizeRequests()
                .antMatchers("/getactualuser").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login")
                .permitAll();
//                .and()
//                .httpBasic();
        httpSecurity.headers().frameOptions().disable();
    }

}
