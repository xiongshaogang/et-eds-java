package com.edaisong.entity;

public class TaskDistributionConfig {
	private int id;
	private int kM;
	private int kG;
	private double distributionPrice;
	private int isMaster;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getkM() {
		return kM;
	}
	public void setkM(int kM) {
		this.kM = kM;
	}
	public int getkG() {
		return kG;
	}
	public void setkG(int kG) {
		this.kG = kG;
	}
	public double getDistributionPrice() {
		return distributionPrice;
	}
	public void setDistributionPrice(double distributionPrice) {
		this.distributionPrice = distributionPrice;
	}
	public int getIsMaster() {
		return isMaster;
	}
	public void setIsMaster(int isMaster) {
		this.isMaster = isMaster;
	}

	
}