package org.frtu.usermgmt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * http://docs.spring.io/spring-security/site/docs/3.2.x/guides/hellomvc.html
 * @author Fred
 */
@Configuration
//@EnableWebSecurity
public class SecurityConfig {// extends WebSecurityConfigurerAdapter {
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//	}


//    @Autowired
//    DataSource dataSource;
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth)
//            throws Exception {
//
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//                .usersByUsernameQuery("sql...")
//                .authoritiesByUsernameQuery("sql...");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder;
//    }
}