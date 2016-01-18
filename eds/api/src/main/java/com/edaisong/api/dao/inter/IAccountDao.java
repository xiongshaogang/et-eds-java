package com.edaisong.api.dao.inter;


import java.util.List;

import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedAccountReq;
import com.edaisong.entity.req.UpdatePwdReq;

public interface IAccountDao {
	
	PagedResponse<Account> query(PagedAccountReq req);
    Account login(String username,String password);
	Account getByID(int userID);
	int updateRoleID(int userID,int newRoleID);
	List<Account> getByRoleID(int roleID);
	int insert(Account account);
	int update(Account account);
	int updatePwd(UpdatePwdReq req);
}