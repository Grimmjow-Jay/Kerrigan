package com.jay.kerrigan.master.service.impl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jay.kerrigan.common.config.KerriganConfig;
import com.jay.kerrigan.common.entity.table.Token;
import com.jay.kerrigan.common.exception.KerriganException;
import com.jay.kerrigan.common.util.UUIDUtil;
import com.jay.kerrigan.master.config.UserInfo;
import com.jay.kerrigan.master.mapper.TokenMapper;
import com.jay.kerrigan.master.service.TokenService;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {

	private static final long TOKEN_EXPIRE_MILLSECONDS = KerriganConfig.getConfig("system.authorization.token.expire",
			TimeUnit.HOURS.toMillis(1));

	@Autowired
	private TokenMapper tokenMapper;

	@Override
	public String login(String userName, String password, String host) {
		KerriganException.assertCondition(!UserInfo.checkUser(userName, password), "Incorrect userName or password");

		Token token = tokenMapper.getByUserNameAndHost(userName, host);
		Date now = new Date();
		if (token == null) {
			token = new Token(UUIDUtil.randomUUID32(), host, userName, now, now);
			tokenMapper.insertToken(token);
		} else {
			token.setTokenId(UUIDUtil.randomUUID32());
			token.setUpdateDate(now);
			tokenMapper.updateToken(token);
		}

		return token.getTokenId();
	}

	@Override
	public boolean logout(String userName, String host, String token) {
		tokenMapper.deleteToken(new Token(token, host, userName, null, null));
		return true;
	}

	@Override
	public boolean checkToken(Token token) {
		if (token == null) {
			return false;
		}

		String tokenId = token.getTokenId();
		String host = token.getHost();
		if (StringUtils.isAnyBlank(tokenId, host)) {
			return false;
		}

		Token oldToken = tokenMapper.getByHostAndTokenId(host, tokenId);
		if (oldToken == null) {
			return false;
		}

		if (oldToken.getUpdateDate().getTime() + TOKEN_EXPIRE_MILLSECONDS < System.currentTimeMillis()) {
			return false;
		}

		return true;
	}

	@Override
	public boolean checkAndUpdateToken(Token token) {
		if (checkToken(token)) {
			token.setUpdateDate(new Date());
			return tokenMapper.extendExpireTime(token) == 1;
		}
		return false;
	}

}
