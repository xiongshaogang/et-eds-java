package com.edaisong.admin.common;

import javax.servlet.http.HttpServletRequest;


import com.edaisong.api.service.inter.IAuthorityMenuClassService;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.util.CookieUtils;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.SpringBeanHelper;
import com.edaisong.entity.Account;


public class UserContext {
	private Account account;
	private boolean isEmpty;
	private int id;
	private int groupId;
	private int roleId;
	private int accountType;
	private String name;


	private final static UserContext empty = new UserContext(null,true);
	private final static RedisService redisService;
	private final static IAuthorityMenuClassService authorityMenuClassService;
	static{
		redisService = SpringBeanHelper.getCustomBeanByType(RedisService.class);
		authorityMenuClassService = SpringBeanHelper.getCustomBeanByType(IAuthorityMenuClassService.class);
	}

	
	public UserContext(Account account,boolean isEmpty){
		this.account = account;
		this.isEmpty = isEmpty;
	}

	public Account getAccount() {
		return account;
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}

	/**
	 * 判断当前登录用户是否有给定菜单的权限
	 * @author hailongzhao
	 * @date 20150828
	 * @param menuID
	 * @return
	 */
	public boolean isHasAuth(int menuID) {
		if (isEmpty) {
			return false;
		}
		return authorityMenuClassService.checkHasAuth(account.getId(), menuID);
	}

	
	public int getId() {
		return id;
	}

	public int getGroupId() {
		return groupId;
	}

	public int getRoleId() {
		return roleId;
	}

	public int getAccountType() {
		return accountType;
	}

	public String getName() {
		return name;
	}


	public static UserContext getCurrentContext(HttpServletRequest request) {
		final String cookieKey = LoginUtil.LOGIN_COOKIE_NAME;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null) {
			Account account = JsonUtil.str2obj(cookieValue, Account.class);
			if(account != null){
				UserContext context = new UserContext(account,false);
				context.id = account.getId();
				context.accountType = ParseHelper.ToInt(account.getAccounttype());
				context.groupId = ParseHelper.ToInt(account.getGroupid());
				context.name = account.getLoginname();
				context.roleId = ParseHelper.ToInt(account.getRoleid());
				return context;
			}
		}
		return empty;
	}
}

