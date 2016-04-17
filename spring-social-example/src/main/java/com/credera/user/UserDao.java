package com.credera.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.jygou.admin.Administrator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.connect.ConnectionKey;

public class UserDao {

	DataSource dataSource;
	
	public UserDao(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public void createUser(ConnectionKey connectionKey) {
		String userSql = "INSERT INTO users " +
				 "(USERNAME, ENABLED) VALUES (?, ?)";
	
		String roleSql = "INSERT INTO authorities " +
						 "(USERNAME, AUTHORITY) VALUES (?, ?)";
		
		java.sql.Connection conn = null;
		
		String username = connectionKey.getProviderId() + "_" + connectionKey.getProviderUserId();
		String role = "ROLE_USER";
		for (Administrator admin: Administrator.values()) {
		    if (admin.getKey().equals(connectionKey)) {
		        role = admin.getRole();
		    }
		}
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(userSql);
			ps.setString(1, username);
			ps.setBoolean(2, true);
			ps.executeUpdate();
			ps.close();
			
			ps = conn.prepareStatement(roleSql);
			ps.setString(1, username);
			ps.setString(2, role);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("SQLException: " + e.getMessage());
				}
			}
		}
	}
	
	public User findUserById(String userId) {
		String userSql = "SELECT AUTHORITY from " +
				 "authorities where username = ?";
		
		java.sql.Connection conn = null;
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(userSql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String authority = rs.getString("AUTHORITY");
				grantedAuthorities.add(new SimpleGrantedAuthority(authority));
			}
			
			ps.close();

		} catch (SQLException e) {
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		User user = new User(userId, grantedAuthorities);
		return user;	
	}
	
}
