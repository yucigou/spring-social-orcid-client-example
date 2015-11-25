package com.credera.springsocial;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

import com.credera.user.UserDao;

public class AccountConnectionSignUp implements ConnectionSignUp {

	private UserDao userDao;
	
	public AccountConnectionSignUp(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public String execute(Connection<?> connection) {
		
		UserProfile profile = connection.fetchUserProfile();
		
		userDao.createUser(profile.getName());
		
		return profile.getName();
	}
}
