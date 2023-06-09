/**
 * 
 */
package com.neu.csye6220.kampus2go;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.neu.csye6220.kampus2go.service.MyUserDetailsService;

/**
 * @author pratiknakave
 *
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService userApi;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userApi);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().and()
                .csrf()
                .disable();

        httpSecurity.authorizeRequests().antMatchers("/").permitAll()
        			.antMatchers("/verify-username").permitAll()
        			.antMatchers("/find-jobs/**").permitAll()
        			.antMatchers("/position/*").permitAll()
        			.antMatchers("/register").permitAll()
        			.antMatchers("/login").permitAll();
        
        httpSecurity.authorizeRequests().antMatchers("/dashboard")
        			.access("hasAnyAuthority('mentee','admin','mentor')");
                httpSecurity.formLogin().loginPage("/login").loginProcessingUrl("/login")
        			.defaultSuccessUrl("/dashboard",true);
        
        httpSecurity.logout().invalidateHttpSession(true).logoutUrl("/logout")
        			.logoutSuccessUrl("/login");
        
        
        httpSecurity.authorizeRequests().antMatchers("/admin-dashboard")
        			.access("hasAuthority('admin')");

        
    }
}
