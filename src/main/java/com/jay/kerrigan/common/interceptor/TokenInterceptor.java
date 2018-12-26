package com.jay.kerrigan.common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TokenInterceptor implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String[] excludes = { "/", "/static/**", "/error", "/rest/login", "/rest/logout" };
		registry.addInterceptor(new TokenInterceptorHolder()).addPathPatterns("/**").excludePathPatterns(excludes);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

}

class TokenInterceptorHolder implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 1. Find token from cookie
		Cookie[] cookies = request.getCookies();
		cookies = cookies == null ? new Cookie[0] : cookies;
		for (Cookie cookie : cookies) {
			if (StringUtils.equals(cookie.getName(), "token")) {
				return true;
			}
		}

		// 2. Find token from parameters
		String token = request.getParameter("token");
		if (!StringUtils.isBlank(token)) {
			return true;
		}

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		request.getRequestDispatcher("/error").forward(request, response);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}