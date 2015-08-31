package com.edaisong.api.common;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.consts.GlobalSettings;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.entity.Business;

public class LoginHelper {
	private final static RedisService redisService;
	static {
		redisService = SpringBeanHelper.getCustomBeanByType(RedisService.class);
	}

	/**
	 * 是否登录
	 * 
	 * @param request
	 * @return
	 */
	public static boolean checkIsLogin(HttpServletRequest request, HttpServletResponse response,String cookieKey) {
		// 如果已登录,直接返回
		boolean isLogin = false;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null) {
			Object loginStatusValue = redisService.get(cookieValue, Object.class);
			if (loginStatusValue != null){// && loginStatusValue instanceof Business) {
				isLogin = true;
			}
		}
		// 如果没有登录,清除旧的登录cookie
		if (!isLogin) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(cookieKey)) {
						CookieUtils.deleteCookie(request, response, cookie);
					}
				}
			}
		}

		return isLogin;
	}

	/**
	 * 存储验证码到redis中
	 * 
	 * @author pengyi
	 * @param code
	 */
	public static void storeAuthCode2Redis(String code, HttpServletRequest request, HttpServletResponse response) {
		String redisKey = UUID.randomUUID().toString();
		redisService.set(redisKey, code);
		CookieUtils.setCookie(request, response, GlobalSettings.JSESSIONID, redisKey, 5 * 24);
	}

	/**
	 * 获取验证码
	 * 
	 * @param request
	 * @author pengyi
	 * @return
	 */
	public static String getAuthCode(HttpServletRequest request) {
		String codeCookie = CookieUtils.getCookie(request, GlobalSettings.JSESSIONID);
		if (codeCookie != null) {
			return redisService.get(codeCookie, String.class);
		}
		return null;
	}

	/**
	 * 删除验证码的cookie
	 * @param request
	 * @param response
	 */
	public static void removeAuthCodeCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = CookieUtils.getCookieByName(GlobalSettings.JSESSIONID, request);
		if (cookie != null) {
			redisService.remove(cookie.getValue());
			CookieUtils.deleteCookie(request, response, cookie);
		}
	}
}