package com.edaisong.toolsapi.dao.inter;

import java.util.List;

import com.edaisong.toolsentity.AuthorityAccountMenuSet;

public interface IAuthorityAccountMenuSetDao {
    List<Integer> getMenuIdsByAccountId(Integer id);
	/**
	 * 修改给定用户的权限列表
	 * @author hailongzhao
	 * @date 20150901
	 * @return
	 */
	public boolean modifyAuthList(List<AuthorityAccountMenuSet> authList) ;
}