package com.jay.kerrigan.master.controller.pageLocation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageLocationController {

	@RequestMapping("/")
	public String toLogin() {
		return "system/login";
	}

}
