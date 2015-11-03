package com.edaisong.entity;

import java.io.Serializable;
import java.lang.Double;
import java.util.Date;

public class OrderChild implements Serializable {
    private Long id;

    private Integer orderid;

    private Integer childid;
    
    private Integer businessid;    
    

    public Integer getBusinessid() {
		return businessid;
	}

	public void setBusinessid(Integer businessid) {
		this.businessid = businessid;
	}

	private Double totalprice;

    private Double goodprice;

    private Double deliveryprice;

    private Short paystyle;

    private Short paytype;

    private Short paystatus;

    private String payby;

    private Date paytime;

    private Double payprice;

    private Boolean hasuploadticket;

    private String ticketurl;

    private String createby;

    private Date createtime;

    private String updateby;

    private Date updatetime;

    private String originalorderno;

    private String wxcodeurl;

    private Short thirdpaystatus;
    private Short status;
    private Double orderCommission;
    private Double settleMoney;
    private Integer orderRegionOneId;
    private Integer orderRegionTwoId;
    public Double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(Double commissionRate) {
		this.commissionRate = commissionRate;
	}

	public Double getBaseCommission() {
		return baseCommission;
	}

	public void setBaseCommission(Double baseCommission) {
		this.baseCommission = baseCommission;
	}

	public Double getWebsiteSubsidy() {
		return websiteSubsidy;
	}

	public void setWebsiteSubsidy(Double websiteSubsidy) {
		this.websiteSubsidy = websiteSubsidy;
	}

	public Double getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(Double adjustment) {
		this.adjustment = adjustment;
	}

	private Double commissionRate;
    private Double baseCommission;
    private Double websiteSubsidy;
    private Double adjustment;

    public Integer getOrderRegionOneId() {
		return orderRegionOneId;
	}

	public void setOrderRegionOneId(Integer orderRegionOneId) {
		this.orderRegionOneId = orderRegionOneId;
	}

	public Integer getOrderRegionTwoId() {
		return orderRegionTwoId;
	}

	public void setOrderRegionTwoId(Integer orderRegionTwoId) {
		this.orderRegionTwoId = orderRegionTwoId;
	}
    public Long getId() {
        return id;
    }

    public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Double getOrderCommission() {
		return orderCommission;
	}

	public void setOrderCommission(Double orderCommission) {
		this.orderCommission = orderCommission;
	}

	public Double getSettleMoney() {
		return settleMoney;
	}

	public void setSettleMoney(Double settleMoney) {
		this.settleMoney = settleMoney;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getChildid() {
        return childid;
    }

    public void setChildid(Integer childid) {
        this.childid = childid;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Double getGoodprice() {
        return goodprice;
    }

    public void setGoodprice(Double goodprice) {
        this.goodprice = goodprice;
    }

    public Double getDeliveryprice() {
        return deliveryprice;
    }

    public void setDeliveryprice(Double deliveryprice) {
        this.deliveryprice = deliveryprice;
    }

    public Short getPaystyle() {
        return paystyle;
    }

    public void setPaystyle(Short paystyle) {
        this.paystyle = paystyle;
    }

    public Short getPaytype() {
        return paytype;
    }

    public void setPaytype(Short paytype) {
        this.paytype = paytype;
    }

    public Short getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(Short paystatus) {
        this.paystatus = paystatus;
    }

    public String getPayby() {
        return payby;
    }

    public void setPayby(String payby) {
        this.payby = payby == null ? null : payby.trim();
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Double getPayprice() {
        return payprice;
    }

    public void setPayprice(Double payprice) {
        this.payprice = payprice;
    }

    public Boolean getHasuploadticket() {
        return hasuploadticket;
    }

    public void setHasuploadticket(Boolean hasuploadticket) {
        this.hasuploadticket = hasuploadticket;
    }

    public String getTicketurl() {
        return ticketurl;
    }

    public void setTicketurl(String ticketurl) {
        this.ticketurl = ticketurl == null ? null : ticketurl.trim();
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getOriginalorderno() {
        return originalorderno;
    }

    public void setOriginalorderno(String originalorderno) {
        this.originalorderno = originalorderno == null ? null : originalorderno.trim();
    }

    public String getWxcodeurl() {
        return wxcodeurl;
    }

    public void setWxcodeurl(String wxcodeurl) {
        this.wxcodeurl = wxcodeurl == null ? null : wxcodeurl.trim();
    }

    public Short getThirdpaystatus() {
        return thirdpaystatus;
    }

    public void setThirdpaystatus(Short thirdpaystatus) {
        this.thirdpaystatus = thirdpaystatus;
    }
}