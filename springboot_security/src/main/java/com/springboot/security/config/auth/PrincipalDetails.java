package com.springboot.security.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.springboot.security.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails ,OAuth2User{
	
	private static final long serialVersionUID = 1L;
	private User user;
	private Map<String , Object> attributes;
	public PrincipalDetails(User user) {
		this.user=user;
	}//일반로그인
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user=user;
		this.attributes=attributes;
	}//oauth2로그인
	
	@Override//권한이 하나가 아닐수도 있기떄문에 collection으로 리턴 collection list ,set 
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return user.getRole();
			}
		});
	
		return collection;//권한
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;//계정이 만료되었는지를 확인
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;//계정이 잠겼니?
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true; // 1년이상 계정을 사용하지 않으면휴먼계정
	}

	@Override
	public boolean isEnabled() {
	
		return true;//계정을 임시 탈퇴 
	}

	@Override
	public Map<String, Object> getAttributes() {
		
		return attributes;
	}

	@Override
	public String getName() {
		
		return (String)attributes.get("name");//오브젝트형태이기 떄문에 String 값으로 다운캐스팅해줘야함
	}
	
}
