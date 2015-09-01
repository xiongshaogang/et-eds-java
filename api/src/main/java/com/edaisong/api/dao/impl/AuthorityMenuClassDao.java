package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IAuthorityMenuClassDao;
import com.edaisong.entity.AuthorityMenuClass;
import com.edaisong.entity.MenuEntity;

@Repository
public class AuthorityMenuClassDao extends DaoBase implements
		IAuthorityMenuClassDao {
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(AuthorityMenuClass record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MenuEntity> getMenuListByUserID(int accountId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accountId", accountId);
		List<MenuEntity> list = getReadOnlySqlSessionUtil()
				.selectList(
						"com.edaisong.api.dao.inter.IAuthorityMenuClassDao.getMenuListByUserID",
						paramMap);
		return list;
	}

	@Override
	public List<MenuEntity> getAuthList(int userID) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"com.edaisong.api.dao.inter.IAuthorityMenuClassDao.getAuthList",userID);
	}

}
