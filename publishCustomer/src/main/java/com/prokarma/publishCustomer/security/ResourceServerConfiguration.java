package com.prokarma.publishCustomer.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.prokarma.publishCustomer.exception.CustomAccessDeniedHandler;
import com.prokarma.publishCustomer.exception.CustomAuthenticationEntryPoint;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "my_rest_api";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID);
	}
	/*
	 * @Override public void configure(HttpSecurity http) throws Exception {
	 * http.anonymous().disable().requestMatchers().antMatchers("/api/**").and().
	 * authorizeRequests()
	 * .antMatchers("/api/**").access("hasRole('ADMIN')").and().exceptionHandling()
	 * .accessDeniedHandler(new CustomAccessDeniedHandler()); }
	 */

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/*").authenticated().antMatchers("/").permitAll();
	}
}