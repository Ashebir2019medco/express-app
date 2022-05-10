package com.api.epharmacy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.api.epharmacy.io.repositories.RoleRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
		        .antMatchers("/", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html",
		            "/**/*.css", "/**/*.js")
		        .permitAll().antMatchers("/auth/**", "/accounts/checkemail/**", "/accounts/resetpassword", "/verify/**",
		            "/roles/addroles", "/**")
		        .permitAll();
		
		//		http.authorizeRequests().antMatchers("/accounts/changestatus/**", "/accounts/users/**", "/accounts/customers/**",
		//		    //"/inventory/**",
		//		    "/pricelist/**", "/order/filter/**", "/order/changeorderstatus/**", "/order/sell/**",
		//		    "/order/changepaymentstatus/**", "/order/filterbypaymnetstatus/**", "/company/search",
		//		    "/inventorytransaction/**", "/accounts/search/**", "/gs-guide-websocket/info").hasAnyRole("ADMIN", "FINANCE");
		
		/*
		 * List<P> roles=(List<RoleEntity>) roleRepository.findAll(); for(RoleEntity
		 * role : roles) { String previlage = role.getPrivilages();
		 * http.authorizeRequests()
		 * .antMatchers(role.getPrivilages()).hasAnyRole(role.getRoleName()); }
		 */
		
		//		http.authorizeRequests().anyRequest().authenticated();
		
		// Add our custom JWT security filter
		//				http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
}
