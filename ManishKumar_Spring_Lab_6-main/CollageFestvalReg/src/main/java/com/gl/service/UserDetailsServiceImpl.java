package com.gl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gl.dao.UserRepository;
import com.gl.entity.User;
import com.gl.security.MyUserDetails;


public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user =userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("could not find user name");
		}
		
		return new MyUserDetails(user);
	}

}
