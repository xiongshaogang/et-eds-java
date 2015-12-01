package com.edaisong.toolsadmin.controller;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.toolsadmin.common.UserContext;
import com.edaisong.toolsapi.common.SQLServerUtil;
import com.edaisong.toolsapi.common.MybatisUtil;
import com.edaisong.toolsapi.common.RedisUtil;
import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolsapi.service.inter.IAppDbConfigService;
import com.edaisong.toolsapi.service.inter.IAuthorityMenuClassService;
import com.edaisong.toolscore.enums.ServerType;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolscore.util.StringUtils;
import com.edaisong.toolsentity.Account;
import com.edaisong.toolsentity.AppDbConfig;
import com.edaisong.toolsentity.AuthorityMenuClass;
import com.edaisong.toolsentity.common.PagedRequestBase;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.common.ResponseBase;
import com.edaisong.toolsentity.domain.ConnectionInfo;
import com.edaisong.toolsentity.domain.MenuEntity;
import com.edaisong.toolsentity.req.PagedAccountReq;
import com.edaisong.toolsentity.req.PagedAppDbConfigReq;
/**
 * 控制其他app
 * @author hailongzhao
 * @date 20151118
 */
@Controller
@RequestMapping("admintools")
public class AdminToolsController {
	@Autowired
	private IAuthorityMenuClassService authorityMenuClassService;
	@Autowired
	private IAppDbConfigService appDbConfigService;
	/**
	 * 根据key模糊查询或精确查询
	 * @param key
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("redismanage")
	public ModelAndView redisManage() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "redis查询工具"); 
		view.addObject("viewPath", "admintools/redismanage");
		view.addObject("appNameList", getappNameList(ServerType.Redis,null));
		return view;
	}
	/**
	 * 根据key模糊查询或精确查询
	 * @param key
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("redisdo")
	@ResponseBody
	public String redisdo(String appName,String key,String sType) {
		List<AppDbConfig> appConfig=getAppConfigList(ServerType.Redis,appName);
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			RedisUtil redisUtil=new RedisUtil(conInfo);
			if(sType.equals("1")){
				Set<String> mSet=redisUtil.keys(key);
				return String.join(",", mSet);
			}else {
				Object m= redisUtil.get(key, Object.class, false);
				if (m==null) {
					return "";
				}
				if (m instanceof List) {
					List<Object> mt=(ArrayList<Object>)m;
					List<String> jsonList=mt.stream().map(t->JsonUtil.obj2string(t)).collect(Collectors.toList());
					return String.join(",", jsonList);
				}else {
					return JsonUtil.obj2string(m);
				}
			}
		}
		return "";
	}
	/**
	 * 菜单管理
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("menulist")
	public ModelAndView menuList(Integer parId) {
		parId = (parId == null ? 0 : parId);
		List<AuthorityMenuClass> resp = authorityMenuClassService.getListMenuByParId(parId);
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "菜单管理");
		view.addObject("viewPath", "admintools/menulist");
		view.addObject("listData", resp);
		view.addObject("currentMenu", authorityMenuClassService.getMenuById(parId));
		view.addObject("appNameList", getappNameList(ServerType.SqlServer,null));
		return view;
	}
	/**
	 * 添加菜单
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
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
	/**
	 * app版本控制
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("appversion")
	public ModelAndView appversion() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "版本控制");
		view.addObject("viewPath", "admintools/appversion");
		return view;
	}
	/**
	 * app数据库配置
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("serverconfig")
	public ModelAndView serverconfig() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "App服务器配置");
		view.addObject("viewPath", "admintools/serverconfig");
		view.addObject("appNameList", getappNameList(null,null));
		return view;
	}
	/**
	 * 查询配置了给定类型的服务器的appName
	 * @param serverType
	 * @return
	 */
	private List<String> getappNameList(ServerType serverType,String appName){
		List<AppDbConfig> resp = getAppConfigList(serverType,appName);
		List<String> appNameList =resp.stream().map(t->t.getAppname()).distinct().collect(Collectors.toList());
		return appNameList;
	}
	/**
	 * 查询配置了给定类型的服务器的设置list
	 * @param serverType
	 * @param appName
	 * @return
	 */
	private List<AppDbConfig> getAppConfigList(ServerType serverType,String appName){
		PagedAppDbConfigReq req=new PagedAppDbConfigReq();
		if (serverType!=null) {
			req.setConfigtype(serverType.value());
		}
		if (appName!=null&&!appName.isEmpty()) {
			req.setAppname(appName);
		}
		return appDbConfigService.queryList(req);	
	}
	/**
	 * app数据库配置分页
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("serverconfigdo")
	public ModelAndView serverconfigdo(PagedAppDbConfigReq req) {
		PagedResponse<AppDbConfig> resp = appDbConfigService.query(req);
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "admintools/serverconfigdo");
		view.addObject("listData", resp);
		return view;
	}
	/**
	 * 删除app数据库配置
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("deleteApp")
	@ResponseBody
	public int deleteApp(Long id) {
		return appDbConfigService.deleteById(id);
	}
	/**
	 * 修改或新增app数据库配置
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("saveapp")
	@ResponseBody
	public int saveapp(ConnectionInfo conInfo,AppDbConfig req,Integer optype,HttpServletRequest request) {
		UserContext context = UserContext.getCurrentContext(request);
		req.setConfigvalue(JsonUtil.obj2string(conInfo));
		req.setCreatename(context.getUserName());
		req.setUpdatename(context.getUserName());
		boolean isPass=checkConnection(conInfo,ServerType.getEnum(req.getConfigtype()));
		if (!isPass) {
			return -1;
		}
		if (optype.equals(0)) {//0表示修改，1表示新增
			return appDbConfigService.updateById(req);
		}else {
			return appDbConfigService.insert(req);
		}
	}
	private boolean checkConnection(ConnectionInfo conInfo,ServerType serverType){
		try {
			switch (serverType) {
			case SqlServer:
				List<Map<String, String>> tList=MybatisUtil.dynamicSelectList(conInfo, "select * from AuthorityMenuClass with(nolock) where id=0");
				break;
			case Redis:
				RedisUtil redisUtil=new RedisUtil(conInfo);
				Object m= redisUtil.get("abc", Object.class, false);
				break;
			case MongoDb:
				break;
			default:
				break;
			}	
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 导出数据管理
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("export")
	public ModelAndView export() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "导出数据管理");
		view.addObject("viewPath", "admintools/export");
		return view;
	}
	/**
	 * 日志分析
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("log")
	public ModelAndView log() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "日志分析");
		view.addObject("viewPath", "admintools/log");
		return view;
	}
	/**
	 * sql查询工具
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("sql")
	public ModelAndView sql() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "sql工具");
		view.addObject("viewPath", "admintools/sql");
		return view;
	}
}