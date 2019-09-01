package org.frtu.usermgmt.security;

import org.apache.log4j.Logger;
import org.frtu.usermgmt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A custom service for retrieving users from a custom datasource, such as a database.
 * <p>
 * This custom service must implement Spring's {@link org.springframework.security.core.userdetails.UserDetailsService}
 */
@Component("userDetailsService")
@Transactional(readOnly = true)
public class SecurityUserDetailsService implements UserDetailsService {
	protected static Logger logger = Logger.getLogger("service");

	@Autowired
	private UserService userService;

	public static String hashPassword(String clearPassword) {
//		http://stackoverflow.com/questions/7658853/spring-security-custom-authentication-and-password-encoding
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(clearPassword);
		return clearPassword;
	}

	/**
	 * Retrieves a user record containing the user's credentials and access.
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		// Declare a null Spring User
		UserDetails userDetails = null;

		try {
			final org.frtu.usermgmt.model.User user = userService.findUserByUsername(username);

			// Populate the Spring User object with details from the User
			// Here we just pass the username, password, and access level
			// getAuthorities() will translate the access level to the correct role type

			userDetails =  new User(
					user.getUsername(),
					user.getPassword().toLowerCase(),
					true,
					true,
					true,
					true,
					getAuthorities(user.getAccessType()) );

		} catch (Exception e) {
			logger.error("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}

		// Return user to Spring for processing.
		// Take note we're not the one evaluating whether this user is authenticated or valid
		// We just merely retrieve a user that matches the specified username
		return userDetails;
	}

	/**
	 * Retrieves the correct ROLE type depending on the access level, where access level is an Integer.
	 * Basically, this interprets the access value whether it's for a regular user or admin.
	 *
	 * @param access an integer value representing the access of the user
	 * @return collection of granted authorities
	 */
	 public Collection<GrantedAuthority> getAuthorities(Integer access) {
			// Create a list of grants for this user
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

			// All users are granted with ROLE_USER access
			// Therefore this user gets a ROLE_USER by default
			logger.debug("Grant ROLE_USER to this user");
			authList.add(new GrantedAuthorityImpl("ROLE_USER"));

			// Check if this user has admin access
			// We interpret Integer(1) as an admin user
			if ( access.compareTo(1) == 0) {
				// User has admin access
				logger.debug("Grant ROLE_ADMIN to this user");
				authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
			}

			// Return list of granted authorities
			return authList;
	  }
}
