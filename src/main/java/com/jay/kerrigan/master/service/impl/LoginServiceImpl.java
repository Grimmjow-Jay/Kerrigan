package com.jay.kerrigan.master.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.jay.kerrigan.common.entity.table.Token;
import com.jay.kerrigan.common.exception.KerriganException;
import com.jay.kerrigan.master.config.UserInfo;
import com.jay.kerrigan.master.repository.TokenRepository;
import com.jay.kerrigan.master.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public String login(String userName, String password, String host) {
		KerriganException.assertCondition(!UserInfo.checkUser(userName, password), "Incorrect userName or password");

		Token token = new Token(null, host+"sss", userName, null, null);
		Example<Token> example = Example.of(token);
		Optional<Token> findOne = tokenRepository.findOne(example);
		Token token2 = findOne.get();
		return token2.getToken();
	}

}
