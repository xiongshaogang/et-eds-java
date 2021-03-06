package com.edaisong.toolsapi.dao.inter;

import com.edaisong.toolsentity.AccountAuthority;

public interface IAccountAuthorityDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountAuthority record);

    int insertSelective(AccountAuthority record);

    AccountAuthority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountAuthority record);

    int updateByPrimaryKey(AccountAuthority record);
}