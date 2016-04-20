package com.credera.user;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public class User {

	private String username;
	
	private String password;
	
	private Set<GrantedAuthority> grantedAuthorities;

	public User (String username, String password, Set<GrantedAuthority> grantedAuthorities) {
		this.username = username;
		this.password = password;
		this.grantedAuthorities = grantedAuthorities;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<GrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	public void setGrantedAuthorities(Set<GrantedAuthority> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
	
	
	
}
