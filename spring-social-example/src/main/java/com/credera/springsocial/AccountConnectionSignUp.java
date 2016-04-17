package com.credera.springsocial;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

import com.credera.user.UserDao;

public class AccountConnectionSignUp implements ConnectionSignUp {

	private UserDao userDao;
	
	public AccountConnectionSignUp(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public String execute(Connection<?> connection) {
		
		// UserProfile profile = connection.fetchUserProfile();
		
		userDao.createUser(connection.getKey());
		
		return connection.getKey().getProviderId() + "_" + connection.getKey().getProviderUserId();
	}
}
