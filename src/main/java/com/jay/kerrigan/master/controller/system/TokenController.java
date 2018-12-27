package com.jay.kerrigan.master.controller.system;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.kerrigan.common.model.ResponseModel;
import com.jay.kerrigan.master.service.TokenService;

@RestController
@RequestMapping("/rest")
public class TokenController {

	@Autowired
	private TokenService loginService;

	@RequestMapping("/login")
	public ResponseModel<String> login(String userName, String password, HttpServletRequest request,
			HttpServletResponse response) {
		String host = request.getRemoteAddr();
		String token = loginService.login(userName, password, host);
		response.addCookie(new Cookie("token", token));
		return ResponseModel.success(token);
	}

	@RequestMapping("/logout")
	public ResponseModel<Boolean> logout(String userName, HttpServletRequest request, HttpServletResponse response) {
		String host = request.getRemoteAddr();
		String token = null;
		for (Cookie cookie : request.getCookies()) {
			if ("token".equals(cookie.getName())) {
				token = cookie.getValue();
				break;
			}
		}
		loginService.logout(userName, host, token);
		Cookie cookie = new Cookie("token", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return ResponseModel.success(true);

	}

}
