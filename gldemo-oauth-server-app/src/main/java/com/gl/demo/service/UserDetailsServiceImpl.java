/**
 * 
 */
package com.gl.demo.service;

import java.util.Optional;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gl.demo.model.User;


/**
 * The Class UserDetailsServiceImpl.
 *
 * @author vikas.kumar3
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = populateUser();
		if(user.isPresent()) {
			return user.get();
		}
		throw new UsernameNotFoundException(username);
	}

	private Optional<User> populateUser() {
		User user = new User();
		user.setUsername("itsMe");
		user.setName("vikas");
		user.setEnabled(true);
		user.setPassword("12345");
		user.setAuthorities(AuthorityUtils.createAuthorityList("Admin","User"));
		return Optional.ofNullable(user);
	}

}
