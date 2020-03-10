package com.vvg.krivanek.warehouserental;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WarehouseRentalConfiguration extends WebSecurityConfigurerAdapter{

	 @Autowired
	    private DataSource dataSource;
	 
	 @Override
	    protected void configure(HttpSecurity http) throws Exception
	    {
	        http
	        .authorizeRequests()
	        .antMatchers(
                    "/",
                    "/css/**",
                    "/fonts/**",
                    "/icons/**",
                    "/img/**",
                    "/jquery/**",
                    "/js/**",
                    "/locales/**").permitAll()
	        .antMatchers("/index").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/getWarehouses")
            .permitAll()
            .and()
            .logout()
            .permitAll()
	        .and()
	        .csrf().disable();    
	    }
	     
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception
	    {
	        auth.jdbcAuthentication().dataSource(dataSource)
	            .authoritiesByUsernameQuery("SELECT USER.username, ROLE.code FROM warehouse_db.user USER, warehouse_db.user_role ROLE where USER.username = ROLE.username AND USER.username = ?")
	            .usersByUsernameQuery("SELECT USER.username, SIGN.value, 1 FROM warehouse_db.user USER, warehouse_db.user_sign SIGN WHERE USER.username = ? AND USER.id = SIGN.user_id");
	    }
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
