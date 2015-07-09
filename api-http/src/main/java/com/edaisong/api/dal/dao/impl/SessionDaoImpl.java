package com.edaisong.api.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IBusinessBalanceRecord;
import com.edaisong.entity.BusinessBalanceRecord;


@Repository
public class SessionDaoImpl implements IBusinessBalanceRecord {
//    @Autowired
//	private SqlSessionFactory superManSqlServerSessionFactory;
    
   @Autowired
	private SqlSessionFactory superManReadOnlySqlServerSessionFactory;
	@Override
	public List<BusinessBalanceRecord> selectBusinessBalanceByID(
			int RecordType, String OperateTime) {

		
		SqlSession session = superManReadOnlySqlServerSessionFactory
				.openSession();

		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("RecordType", RecordType);
			paramMap.put("OperateTime", OperateTime);
			List<BusinessBalanceRecord> list = session
					.selectList(
							"com.yihaomen.mybatis.models.UserMapper.selectBusinessBalanceByID",
							paramMap);
			if (list == null) {
				System.out.println("selectBusinessBalanceByID为null");
			} else {
				System.out.println("selectBusinessBalanceByID记录个数:" + list.size());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	@Override
	public int addBusinessBalance(BusinessBalanceRecord record) {
			
		SqlSession session = superManReadOnlySqlServerSessionFactory
				.openSession();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("BusinessId", "902");
			paramMap.put("Amount", 500);
			paramMap.put("Status", 1);
			paramMap.put("Balance", 900);
			paramMap.put("RecordType", 9);
			paramMap.put("Operator", "zhaohl");
			paramMap.put("OperateTime", "2015-1-1");

			paramMap.put("WithwardId", "123");
			paramMap.put("RelationNo", "55555");
			paramMap.put("Remark", "mybatistest");
			int result = session
					.insert("com.yihaomen.mybatis.models.UserMapper.addBusinessBalance",
							paramMap);
			session.commit(); 
			System.out.println("addBusinessBalance影响行数" + result);
		} catch (Exception e) {
			System.out.println("addBusinessBalance-Exception异常" + e.getMessage());
		} finally {
			session.close();
		}
		return 0;
	}

	@Override
	public int updateBusinessBalance(BusinessBalanceRecord record) {
		SqlSession session = superManReadOnlySqlServerSessionFactory
				.openSession();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("id", 164);
			paramMap.put("Amount", 100);
			paramMap.put("Balance", 100);
			int result = session
					.update("com.yihaomen.mybatis.models.UserMapper.updateBusinessBalance",
							paramMap);
			session.commit();
			System.out.println("updateBusinessBalance影响行数" + result);
		} catch (Exception e) {
			System.out.println("updateBusinessBalance-Exception异常" + e.getMessage());
		} finally {
			session.close();
		}
		return 0;
	}

	@Override
	public int deleteBusinessBalance(int id) {
		SqlSession session = superManReadOnlySqlServerSessionFactory
				.openSession();
		try {
			int result = session
					.delete("com.yihaomen.mybatis.models.UserMapper.deleteBusinessBalance",
							id);
			session.commit();
			System.out.println("deleteBusinessBalance影响行数" + result);
		} catch (Exception e) {
			System.out.println("deleteBusinessBalance-Exception异常" + e.getMessage());
		} finally {
			session.close();
		}
		return 0;
	}
}
