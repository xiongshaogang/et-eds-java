package com.edaisong.toolsapi.dao.inter;



import java.util.List;

import com.edaisong.toolsentity.AuthorityRole;

public interface IAuthorityRoleDao {
    int insert(AuthorityRole record);

    int update(AuthorityRole record);
    List<AuthorityRole> selectList();
}