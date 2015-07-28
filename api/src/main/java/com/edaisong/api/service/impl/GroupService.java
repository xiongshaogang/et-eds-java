package com.edaisong.api.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.edaisong.api.dal.dao.inter.IGroupDao;
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.entity.Group;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.resp.GroupResp;

@Service
public class GroupService implements IGroupService {

	@Autowired
	private IGroupDao dao;
	@Override
	public GroupResp getGroupListByID(GroupReq req) {
		// TODO Auto-generated method stub
		GroupResp resp = new GroupResp();
		List<GroupModel> listData = dao.getGroupListByID(
				req.getId());
		resp.setGroupList(listData);
		return resp;
	}

	@Override
	public GroupResp getGroupList(GroupReq req) {
		// TODO Auto-generated method stub
		GroupResp resp = new GroupResp();
		List<GroupModel> listData = dao.getGroupList(
				req);
		resp.setGroupList(listData);
		return resp;
	}

	@Override
	public int Add(Group record) {
		// TODO Auto-generated method stub
		return dao.insert(record);
	}
	
	@Override
	public int  Update(Group record) 
	{
		return dao.updateByPrimaryKeySelective(record);
	}

   
}