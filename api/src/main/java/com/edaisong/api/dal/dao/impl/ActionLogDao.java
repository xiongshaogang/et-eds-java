package com.edaisong.api.dal.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dal.dao.inter.IActionLogDao;
import com.edaisong.entity.domain.ActionLog;

@Repository
public class ActionLogDao extends DaoBase implements IActionLogDao {

	@Override
	public void writeActionLog(ActionLog logEngity) {
		getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dal.dao.inter.IActionLogDao.WriteActionLog",
				logEngity);

	}

	@Override
	public void writeLog2DB(String msg) {
		getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dal.dao.inter.IActionLogDao.WriteActionLog",
				msg);

	}

}
