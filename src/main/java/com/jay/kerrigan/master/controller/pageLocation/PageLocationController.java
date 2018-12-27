package com.jay.kerrigan.master.controller.pageLocation;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jay.kerrigan.common.entity.table.Token;
import com.jay.kerrigan.master.service.TokenService;

@Controller
public class PageLocationController {

	@Autowired
	private TokenService tokenService;

	@RequestMapping("/")
	public String toLogin(HttpServletRequest request) {
		Token token = new Token();
		token.setHost(request.getRemoteAddr());

		// Find token from session
		HttpSession session = request.getSession();
		Object tokenAttribute = session.getAttribute("token");
		if (tokenAttribute != null) {
			token.setTokenId(String.valueOf(tokenAttribute));
			if (tokenService.checkAndUpdateToken(token)) {
				return "kerrigan/index";
			}
		}

		// Find token from cookie
		Cookie[] cookies = request.getCookies();
		cookies = cookies == null ? new Cookie[0] : cookies;
		for (Cookie cookie : cookies) {
			if ("token".equals(cookie.getName())) {
				token.setTokenId(cookie.getValue());
				if (tokenService.checkAndUpdateToken(token)) {
					return "kerrigan/index";
				}
			}
		}
		return "system/login";

	}

}
