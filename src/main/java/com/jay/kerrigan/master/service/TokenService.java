package com.jay.kerrigan.master.service;

import com.jay.kerrigan.common.entity.table.Token;

public interface TokenService {

	String login(String userName, String password, String host);

	boolean logout(String userName, String host, String token);

	boolean checkToken(Token token);

	boolean checkAndUpdateToken(Token token);
}
