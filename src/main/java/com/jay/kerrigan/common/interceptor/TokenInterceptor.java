package com.jay.kerrigan.common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jay.kerrigan.Kerrigan;
import com.jay.kerrigan.KerriganSlave;
import com.jay.kerrigan.common.config.KerriganConfig;
import com.jay.kerrigan.common.entity.table.Token;
import com.jay.kerrigan.master.service.TokenService;

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

	private static TokenService tokenService = getTokenMapperBean();

	private static final boolean AUTHORIZATION_OPEN = KerriganConfig.getConfig("system.authorization.open", true);

	private static TokenService getTokenMapperBean() {
		try {
			return Kerrigan.getBean(TokenService.class);
		} catch (BeansException e) {
			return null;
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// KerriganSlave don't check token
		if (Kerrigan.getKerriganRole() == KerriganSlave.class || tokenService == null) {
			return true;
		}

		// Authorization is not available
		if (!AUTHORIZATION_OPEN) {
			return true;
		}

		Token token = new Token();
		token.setHost(request.getRemoteAddr());

		// Find token from cookie
		Cookie[] cookies = request.getCookies();
		cookies = cookies == null ? new Cookie[0] : cookies;
		for (Cookie cookie : cookies) {
			if (StringUtils.equals(cookie.getName(), "token")) {
				token.setTokenId(cookie.getValue());
				return tokenService.checkAndUpdateToken(token);
			}
		}

		// Find token from parameters
		String tokenId = request.getParameter("token");
		if (StringUtils.isBlank(tokenId)) {
			return false;
		}

		token.setTokenId(tokenId);
		if (tokenService.checkAndUpdateToken(token)) {
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