package com.crimealert.security;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import com.crimealert.repository.UserRepository;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserRepository userRepository;

	@Autowired
    private DataSource dataSource;
	
	public SecurityConfig() {
		log.debug("creating spring security configuration");
	}
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    	 auth
         .jdbcAuthentication()
         	 .dataSource(dataSource)
         	 .usersByUsernameQuery("select email, password, true from tbl_user where email=? and enabled = true")
         	 .authoritiesByUsernameQuery("select email as username, role from tbl_user where email = ? and enabled = true");
    	
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
           .antMatchers("/resources/**"); // #3
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	AccessDeniedHandlerImpl hl = new AccessDeniedHandlerImpl();
    	hl.setErrorPage("/accessdenied");
    	
        http
        	.csrf().disable()
        	.authorizeRequests()
        		.antMatchers("/").permitAll()
        		.antMatchers("/register").permitAll()
        		.antMatchers("/forgotpassword").permitAll()
        		.antMatchers("/changeforgotpassword").permitAll()
        		.antMatchers("/resetforgotpassword/**").permitAll()
        		.antMatchers("/verify/**").permitAll()
        		.antMatchers("/reports/**").permitAll()
        		.antMatchers("/registeruser").permitAll()
        		//.antMatchers("/generate").permitAll()
        		.antMatchers("/search").permitAll()
        		.antMatchers("/complaint/list/all").hasAnyAuthority("MANAGER,ADMIN")
        		.antMatchers("/crime/list/all").hasAnyAuthority("MANAGER,ADMIN")
        		.antMatchers("/user/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
            .logout()
            	.logoutSuccessUrl("/login")
            	.and()
            .formLogin()
                .loginPage("/login") 
                .permitAll();
        
        http.exceptionHandling().accessDeniedHandler(hl);
    	
    }

}