package com.edaisong.toolsadmin.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.toolsadmin.common.MenuHelper;
import com.edaisong.toolsapi.service.inter.IAccountService;
import com.edaisong.toolsapi.service.inter.IAuthorityAccountMenuSetService;
import com.edaisong.toolsapi.service.inter.IAuthorityMenuClassService;
import com.edaisong.toolsapi.service.inter.IAuthorityRoleService;
import com.edaisong.toolscore.util.StringUtils;
import com.edaisong.toolsentity.Account;
import com.edaisong.toolsentity.AuthorityAccountMenuSet;
import com.edaisong.toolsentity.AuthorityMenuClass;
import com.edaisong.toolsentity.AuthorityRole;
import com.edaisong.toolsentity.MenuEntity;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.common.ResponseBase;
import com.edaisong.toolsentity.req.AddNewMenuReq;
import com.edaisong.toolsentity.req.PagedAccountReq;

@Controller
@RequestMapping("authmanage")
public class AuthManageController {
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAuthorityMenuClassService authorityMenuClassService;
	@Autowired
	private IAuthorityAccountMenuSetService authorityAccountMenuSetService;
	@Autowired
	private IAuthorityRoleService authorityRoleService;
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "个人账户权限管理");
		model.addObject("viewPath", "authmanage/list");
		List<AuthorityRole> datalist=authorityRoleService.selectList();
		model.addObject("roleData", datalist);
		return model;
	}

	@RequestMapping("listdo")
	public ModelAndView listdo(PagedAccountReq req) {
		PagedResponse<Account> resp = accountService.queryAccount(req);
		ModelAndView model = new ModelAndView("authmanage/listdo");
		model.addObject("listData", resp);
		return model;
	}
	@RequestMapping("getroleid")
	@ResponseBody
	public int getRoleID(int userID) {
		Account userAccount = accountService.getByID(userID);
		if (userAccount!=null) {
			return userAccount.getRoleid();
		}
		return -1;
	}
	@RequestMapping("updateroleid")
	@ResponseBody
	public int updateRoleID(int userID,int newRoleID) {
		return accountService.updateRoleID(userID, newRoleID);
	}
	@RequestMapping(value = "authlist", produces= "application/json; charset=utf-8")
	@ResponseBody
	public String getAuthList(int userID) {
		List<MenuEntity> menuList = authorityMenuClassService.getAuthSettingList(userID);
		return MenuHelper.getAuthJson(menuList);
	}

	@RequestMapping("saveauth")
	@ResponseBody
	public String saveauth(int userID, String newAuth, String oldAuth) {
		List<String> newList = new ArrayList<>();
		List<String> oldList = new ArrayList<>();
		List<String> diffList = new ArrayList<>();
		if (newAuth != null && !newAuth.isEmpty()) {
			Collections.addAll(newList, newAuth.split(","));
			Collections.addAll(diffList, newAuth.split(","));
		}
		if (oldAuth != null && !oldAuth.isEmpty()) {
			Collections.addAll(oldList, oldAuth.split(","));
		}

		diffList.removeAll(oldList);// diffList中剩余的是新增的权限id
		oldList.removeAll(newList);// oldList中剩余的是被删掉的权限id
		diffList.addAll(oldList);// diffList中剩余的是发生了变更的权限id
		if (diffList.size() == 0) {
			return "没有任何修改，不需要保存";
		}
		List<AuthorityAccountMenuSet> authList = new ArrayList<>();
		for (String authid : diffList) {
			AuthorityAccountMenuSet authset = new AuthorityAccountMenuSet();
			authset.setAccoutid(userID);
			authset.setMenuid(Integer.parseInt(authid));
			authList.add(authset);
		}
		authorityAccountMenuSetService.modifyAuthList(authList);

		return "";
	}

	@RequestMapping("menulist")
	public ModelAndView menuList(Integer parId) {
		parId = (parId == null ? 0 : parId);
		List<AuthorityMenuClass> resp = authorityMenuClassService.getListMenuByParId(parId);
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "权限");
		view.addObject("currenttitle", "菜单管理");
		view.addObject("viewPath", "authmanage/menulist");
		view.addObject("listData", resp);
		view.addObject("currentMenu", authorityMenuClassService.getMenuById(parId));
		return view;
	}
	
	@RequestMapping("addnewmenu")
	@ResponseBody
	public ResponseBase addNewMenu(AuthorityMenuClass req){
		ResponseBase resp = new ResponseBase();
		if(StringUtils.isEmpty(req.getMenuname())){
			resp.setMessage("请填写菜单名称");
		}else{
			int curId = req.getParid() == null ? 0 : req.getParid();
			req.setParid(curId);
			req.setBelock(false);
			authorityMenuClassService.addMenu(req);
			
			resp.setMessage("添加菜单成功");
			resp.setResponseCode(1);
		}
		
		
		return resp;
	}
}
