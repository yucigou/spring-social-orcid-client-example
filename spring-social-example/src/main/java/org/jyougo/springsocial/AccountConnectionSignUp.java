package org.jyougo.springsocial;

import org.jyougo.user.UserDao;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionSignUp;

public class AccountConnectionSignUp implements ConnectionSignUp {

	private UserDao userDao;
	
	public AccountConnectionSignUp(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public String execute(Connection<?> connection) {
		
	    ConnectionData connectionData = connection.createData();
	    String accessToken = connectionData.getAccessToken();
	    
		userDao.createUser(connection.getKey(), accessToken);
		
		return connection.getKey().getProviderId() + "_" + connection.getKey().getProviderUserId();
	}
}
