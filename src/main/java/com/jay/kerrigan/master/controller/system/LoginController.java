package com.jay.kerrigan.master.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.kerrigan.common.model.ResponseModel;
import com.jay.kerrigan.master.service.LoginService;

@RestController
@RequestMapping("/rest")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping("/login")
	public ResponseModel<String> login(String userName, String password, HttpServletRequest request) {
		String host = request.getRemoteAddr();
		return ResponseModel.success(loginService.login(userName, password, host));
	}

}
