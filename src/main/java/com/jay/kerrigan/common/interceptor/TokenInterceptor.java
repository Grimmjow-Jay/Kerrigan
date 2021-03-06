package com.jay.kerrigan.common.interceptor;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.jay.kerrigan.common.config.KerriganConfig;
import com.jay.kerrigan.common.entity.table.Token;
import com.jay.kerrigan.master.service.TokenService;
import com.jay.kerrigan.slave.KerriganSlave;

@Configuration
public class TokenInterceptor implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String[] excludes = { "/", "/static/**", "/error", "/auth/**" };
		registry.addInterceptor(new TokenInterceptorHolder()).addPathPatterns("/**").excludePathPatterns(excludes);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

}

class TokenInterceptorHolder implements HandlerInterceptor {

	private static final long TOKEN_EXPIRE_MILLSECONDS = KerriganConfig.getConfig("system.authorization.token.expire",
			TimeUnit.HOURS.toMillis(1));
	private static final boolean AUTHORIZATION_OPEN = KerriganConfig.getConfig("system.authorization.open", true);

	private static final String[] BROWSER_SIGN = { "Mozilla", "AppleWebKit", "Chrome", "Safari", "Firefox", "Edge" };

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
		if (Kerrigan.role == KerriganSlave.class) {
			return true;
		}

		// If TokenService is not available, return true
		final TokenService tokenService = getTokenMapperBean();
		if (tokenService == null) {
			return true;
		}

		// Authorization is not available
		if (!AUTHORIZATION_OPEN) {
			return true;
		}

		Token token = new Token();
		token.setHost(request.getRemoteAddr());

		// Find token from session
		HttpSession session = request.getSession(true);
		if (session != null) {
			token.setTokenId(String.valueOf(session.getAttribute("token")));
			if (tokenService.checkAndUpdateToken(token)) {
				return true;
			}
		}

		// Find token from cookie
		Cookie[] cookies = request.getCookies();
		cookies = cookies == null ? new Cookie[0] : cookies;
		for (Cookie cookie : cookies) {
			if (StringUtils.equals(cookie.getName(), "token")) {
				token.setTokenId(cookie.getValue());
				if (tokenService.checkAndUpdateToken(token)) {
					session.setAttribute("token", token.getTokenId());
					session.setMaxInactiveInterval((int) (TOKEN_EXPIRE_MILLSECONDS / 1000));
					return true;
				}
			}
		}

		// Find token from parameters
		String tokenId = request.getParameter("token");
		token.setTokenId(tokenId);
		if (tokenService.checkAndUpdateToken(token)) {
			session.setAttribute("token", token.getTokenId());
			session.setMaxInactiveInterval((int) (TOKEN_EXPIRE_MILLSECONDS / 1000));
			return true;
		}

		// Do not found token still
		// If request comes from browser but not an ajax, forward to login page
		// else forward to error page
		if ("GET".equalsIgnoreCase(request.getMethod())
				&& !"XMLHttpRequest".equals(request.getHeader("x-requested-with"))
				&& StringUtils.containsAny(request.getHeader("user-agent"), BROWSER_SIGN)) {
			response.sendRedirect("/");
		} else {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			request.getRequestDispatcher("/error").forward(request, response);
		}

		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}