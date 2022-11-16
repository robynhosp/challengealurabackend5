package com.alura.challenge.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.alura.challenge.security.AutenticacaoService;


@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	    .antMatchers(HttpMethod.GET,"/videos").permitAll()
	    .antMatchers(HttpMethod.GET,"/videos/*").permitAll()
	    .anyRequest().authenticated()
	    .and().formLogin();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		
	}
		
}
