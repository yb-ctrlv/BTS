package com.kh.bts.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@ComponentScan(basePackages= {"com.kh.bts.*"})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationProvider authProvider;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
		//허용 되어야 할 경로들
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/loginform","/login","/error").permitAll()
			.antMatchers("/**").access("hasRole('ROLE_ADMIN')")
		.and()
			.formLogin()
			.loginPage("/loginform")
			.loginProcessingUrl("/login")
			.failureUrl("/loginform?error=1")
			.defaultSuccessUrl("/")
			.usernameParameter("id")
			.passwordParameter("pw")
		.and()
			.exceptionHandling()
			.accessDeniedPage("/error")
		.and()
			.logout()
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
		.and()
			.csrf()
		.and()
			.authenticationProvider(authProvider);
			
	}
}
