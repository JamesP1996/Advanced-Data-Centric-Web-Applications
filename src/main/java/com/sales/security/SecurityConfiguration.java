package com.sales.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

// Security Configuration Class. To Create Users
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
            // Create User and Password using the NoOp Password Encoder, To Ensure Security Integrity.
        		.withUser("user").password("password")
                .roles("USER", "ADMIN");
    }
	
	// Configure the Relevant Links Where a Login Will be Required. (Basically all of them).
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login", "/h2-console/**").permitAll()
                .antMatchers(
                		"/addCustomer.html", "/addProduct.html","/newOrder.html",
                		"/showCustomers.html","/showOrders.html","/showProducts.html","/orderError.html","orderError2.html")
                .access("hasRole('USER')").and()
                .formLogin();
        // Disable CRSF Tokenization, for the Development Use Case. To Allow User To create and read data without Issue.
        // In a Real World Application. Each html page will need to have CRSF tokens applies to them to ensure that information is not tampered with
        // During Transactions.
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}