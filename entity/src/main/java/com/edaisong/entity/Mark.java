package com.edaisong.entity;

import java.util.Date;

public class Mark {
    private Integer id;

    private String markName;

    private Integer markType;

    private Integer bindquantity;

    private Integer isenable;

    private Date createtime;

    private String createname;

    private Date modifytime;

    private String modifyname;
    
    private String remark;

    private Integer isdelete;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarkName() {
		return markName;
	}

	public void setMarkName(String markName) {
		this.markName = markName;
	}

	public Integer getMarkType() {
		return markType;
	}

	public void setMarkType(Integer markType) {
		this.markType = markType;
	}

	public Integer getBindquantity() {
		return bindquantity;
	}

	public void setBindquantity(Integer bindquantity) {
		this.bindquantity = bindquantity;
	}

	public Integer getIsenable() {
		return isenable;
	}

	public void setIsenable(Integer isenable) {
		this.isenable = isenable;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreatename() {
		return createname;
	}

	public void setCreatename(String createname) {
		this.createname = createname;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public String getModifyname() {
		return modifyname;
	}

	public void setModifyname(String modifyname) {
		this.modifyname = modifyname;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
    
}