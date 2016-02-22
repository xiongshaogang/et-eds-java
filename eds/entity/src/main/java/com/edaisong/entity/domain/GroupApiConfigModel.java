package com.edaisong.entity.domain;

import java.util.Date;

/**
 * 集团配置domain
 * 
 * @author CaoHeYang
 * @date 20160118
 */
public class GroupApiConfigModel {
	private int id;
	private String appKey;
	private String appSecret;
	private String appVersion;
	private int groupId;
	private String groupName;
	private byte isValid;
	private String createName;
	private Date createTime;
	
	/**
	 * 登录帐号
	 */
	private String phoneNo;

	/**
	 * 审核状态 0待审核 1审核通过 2 审核拒绝
	 */
	private Integer auditStatu;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * appKey
	 * 
	 * @return
	 */
	public String getAppKey() {
		return appKey;
	}

	/**
	 * appKey
	 * 
	 * @param appKey
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**
	 * appSecret
	 * 
	 * @return
	 */
	public String getAppSecret() {
		return appSecret;
	}

	/**
	 * appSecret
	 * 
	 * @param appSecret
	 */
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	/**
	 * appVersion
	 * 
	 * @return
	 */
	public String getAppVersion() {
		return appVersion;
	}

	/**
	 * appVersion
	 * 
	 * @param appVersion
	 */
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	/**
	 * 第三方平台id
	 * 
	 * @return
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * 第三方平台id
	 * 
	 * @param groupId
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * 第三方平台名称
	 * 
	 * @return
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * 第三方平台名称
	 * 
	 * @param groupName
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * 是否有效
	 * 
	 * @return
	 */
	public byte getIsValid() {
		return isValid;
	}

	/**
	 * 是否有效
	 * 
	 * @param isValid
	 */
	public void setIsValid(byte isValid) {
		this.isValid = isValid;
	}

	/**
	 * 创建人
	 * 
	 * @return
	 */
	public String getCreateName() {
		return createName;
	}

	/**
	 * 创建人
	 * 
	 * @param createName
	 */
	public void setCreateName(String createName) {
		this.createName = createName;
	}

	/**
	 * 创建时间
	 * 
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取登录帐号
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * 设置登录帐号
	 * @param phoneNo 登录帐号
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * 获取审核状态 0待审核 1审核通过 2 审核拒绝
	 */
	public Integer getAuditStatu() {
		return auditStatu;
	}
	/**
	 * 设置审核状态 0待审核 1审核通过 2 审核拒绝
	 * @param auditStatu 审核状态 0待审核 1审核通过 2 审核拒绝
	 */
	public void setAuditStatu(Integer auditStatu) {
		this.auditStatu = auditStatu;
	}


}