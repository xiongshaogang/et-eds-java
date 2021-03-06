package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.dao.inter.IBusinessClienterRelationDao;
import com.edaisong.api.dao.inter.IBusinessDao;
import com.edaisong.api.dao.inter.IClienterDao;
import com.edaisong.api.service.inter.IBusinessClienterRelationService;
import com.edaisong.entity.BusinessClienterRelation;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClienterRelationModel;
import com.edaisong.entity.req.ClienterBindOptionReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;

@Service
public class BusinessClienterRelationService implements IBusinessClienterRelationService {

	@Autowired
	private IBusinessClienterRelationDao businessClienterRelationDao;
	@Autowired
	private IBusinessDao businessDao;
	@Autowired
	private IClienterDao clienterDao;

	@Override
	public int getBusinessBindClienterQty(int businessId) {
		return businessClienterRelationDao.getBusinessBindClienterQty(businessId);
	}

	@Override
	public PagedResponse<BusinessClienterRelationModel> getBusinessClienterRelationList(PagedCustomerSearchReq req) {
		return businessClienterRelationDao.getBusinessClienterRelationList(req);
	}

	/**
	 * 修改骑士绑定
	 * @author pengyi
	 * @date 20150901
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	@Override
	public boolean modifyClienterBind(ClienterBindOptionReq req) {
		boolean flag = false;
		if (businessClienterRelationDao.modifyClienterBind(req)) {
			if (req.getIsBind() == 1) {// 绑定
				if (businessDao.updateBusinessIsBind(req.getBusinessId(), 1)) {
					if (clienterDao.updateClienterIsBind(req.getClienterId(), 1)) {
						flag = true;
					}
				}
			} else {// 解绑
				if (businessClienterRelationDao.getClienterBindBusinessQty(req.getClienterId()) > 0) {
					if (businessClienterRelationDao.getBusinessBindClienterQty(req.getBusinessId()) > 0) {
						flag = true;
					} else {
						if (businessDao.updateBusinessIsBind(req.getBusinessId(), 0)) {
							flag = true;
						}
					}
				} else {
					if (clienterDao.updateClienterIsBind(req.getClienterId(), 0)) {
						if (businessClienterRelationDao.getBusinessBindClienterQty(req.getBusinessId()) > 0) {
							flag = true;
						} else {
							if (businessDao.updateBusinessIsBind(req.getBusinessId(), 0)) {
								flag = true;
							}
						}
					}
				}
			}
		}
		if (!flag) {
			throw new RuntimeException("修改骑士绑定失败");
		}
		return flag;
	}

	/**
	 * 删除骑士绑定
	 * @author pengyi
	 * @date 20150901
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	@Override
	public boolean removeclienterbind(ClienterBindOptionReq req) {
		boolean flag = false;
		if (businessClienterRelationDao.removeclienterbind(req)) {
			if (businessClienterRelationDao.getClienterBindBusinessQty(req.getClienterId()) > 0) {
				if (businessClienterRelationDao.getBusinessBindClienterQty(req.getBusinessId()) > 0) {
					flag = true;
				}
			} else {
				if (clienterDao.updateClienterIsBind(req.getClienterId(), 0)) {
					if (businessClienterRelationDao.getBusinessBindClienterQty(req.getBusinessId()) > 0) {
						flag = true;
					} else {
						if (businessDao.updateBusinessIsBind(req.getBusinessId(), 0)) {
							flag = true;
						}
					}
				}
			}
		}
		if (!flag) {
			throw new RuntimeException("修改骑士绑定失败");
		}
		return flag;
	}

	/**
	 * 确实骑士是否已绑定商家
	 * @author pengyi
	 * @date 20150901
	 */
	@Override
	public boolean checkHaveBind(ClienterBindOptionReq req) {
		return businessClienterRelationDao.checkHaveBind(req);
	}

	/**
	 * 添加骑士绑定
	 * @author pengyi
	 * @date 20150901
	 * @param req
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public boolean addClienterBind(ClienterBindOptionReq req){
		boolean reg = false;
		if (businessClienterRelationDao.addClienterBind(req)){
            if (businessDao.updateBusinessIsBind(req.getBusinessId(), 1)){
                if (clienterDao.updateClienterIsBind(req.getClienterId(), 1)){
                    reg = true;
                }
            }
        }
		if (!reg) {
			throw new RuntimeException("添加骑士绑定失败");
		}
        return reg;
	}

	@Override
	public BusinessClienterRelation getDetails(int businessId, int clienterId) {
		return businessClienterRelationDao.getDetails(businessId,clienterId);
	}
}
