package com.jay.kerrigan.master.service;

public interface LoginService {

	String login(String userName, String password, String host);
	
	boolean logout(String userName, String host);
}