package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IAccountDao;
import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedAccountReq;

@Repository
public class AccountDao extends DaoBase implements IAccountDao {
	// 查询所有管理后台用户列表
	@Override
	public PagedResponse<Account> query(PagedAccountReq req) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IAccountDao.query", req);
	}

	@Override
	public Account login(String username, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);
		return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IAccountDao.login", params);
	}

	@Override
	public Account getByID(int userID) {
		return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IAccountDao.getByID", userID);
	}

	@Override
	public int updateRoleID(int userID, int newRoleID) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userID", userID);
		params.put("newRoleID", newRoleID);
		return getMasterSqlSessionUtil().update(
				"com.edaisong.api.dao.inter.IAccountDao.updateRoleID", params);
	}

	@Override
	public List<Account> getByRoleID(int roleID) {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IAccountDao.getByRoleID", roleID);
	}

}
