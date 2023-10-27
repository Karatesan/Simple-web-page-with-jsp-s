package com.fdmgroup.GameBlog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;
	
	@Autowired
	public SecurityConfig(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}
	
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/css/**", "/js/**", "/h2/**", "WEB-INF/jsps/**", "/", "/**/*.png", "/**/*.PNG", "/**/*.jpg", "/**/*.JPG", "/register",  "/goRegisterPage", "/goQuestionPassword","/posts/{id}", "/questionPassword", "/resetPassword", "/goToSearchingPage", "/goToAbout", "/login", "/toContact" ).permitAll()
			.antMatchers("/admin/**").hasRole("Admin")
			.antMatchers("/posts/new", "/posts/{id}/edit", "/posts/{id}/delete").hasAnyRole("Admin","Author")
			.anyRequest().authenticated()
			.and()
		.formLogin().loginPage("/login").permitAll()
			.defaultSuccessUrl("/", false)
			.failureUrl("/login-error")
			.and()
		.logout()
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.and()
		.csrf()
			.disable()	
		.httpBasic()
			.and()
		.headers().frameOptions().disable();
	}

}
