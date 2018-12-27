package com.jay.kerrigan.master.controller.system;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.kerrigan.common.config.KerriganConfig;
import com.jay.kerrigan.common.model.ResponseModel;
import com.jay.kerrigan.master.service.TokenService;

@RestController
@RequestMapping("/auth")
public class TokenController {

	@Autowired
	private TokenService loginService;

	@RequestMapping("/login")
	public ResponseModel<String> login(String userName, String password, HttpServletRequest request,
			HttpServletResponse response) {
		String host = request.getRemoteAddr();
		String token = loginService.login(userName, password, host);

		// Set token in cookie
		response.addCookie(new Cookie("token", token));

		// Set token in session
		HttpSession session = request.getSession(true);
		session.setAttribute("token", token);
		session.setMaxInactiveInterval((int) (KerriganConfig.TOKEN_EXPIRE_MILLSECONDS / 1000));

		// return token
		return ResponseModel.success(token);
	}

	@RequestMapping("/logout")
	public ResponseModel<Boolean> logout(String userName, HttpServletRequest request, HttpServletResponse response) {
		String host = request.getRemoteAddr();
		String tokenId = null;
		for (Cookie cookie : request.getCookies()) {
			if ("token".equals(cookie.getName())) {
				tokenId = cookie.getValue();
				break;
			}
		}
		loginService.logout(userName, host, tokenId);

		Cookie cookie = new Cookie("token", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		request.getSession().removeAttribute("token");

		return ResponseModel.success(true);

	}

}
