package com.springboot.security.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.security.domain.user.User;
import com.springboot.security.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDeatilsService implements UserDetailsService {
	private final UserRepository userrepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity=userrepository.getUser(username);
		if(userEntity==null) {
			return null;
		}else {
			UserDetails principaldetails = new PrincipalDetails(userEntity);
			return principaldetails;
		}
	}
}
