package com.yjm.model;

public class Services {
	public int getSerid() {
		return serid;
	}
	public void setSerid(int serid) {
		this.serid = serid;
	}
	public String getSername() {
		return sername;
	}
	public void setSername(String sername) {
		this.sername = sername;
	}
	public int getSermoney() {
		return sermoney;
	}
	public void setSermoney(int sermoney) {
		this.sermoney = sermoney;
	}
	public String getSertype() {
		return sertype;
	}
	public void setSertype(String sertype) {
		this.sertype = sertype;
	}

	private int serid;
	private String sername;
	private int sermoney;
	private String sertype;
	
	
	public Services(){};
	public Services(int serid,String sername,int sermoney){
		this.serid = serid;
		this.sername = sername;
		this.sermoney = sermoney;
	};
	public Services(String sername,int sermoney,String sertype){
		this.sername = sername;
		this.sermoney = sermoney;
		this.sertype = sertype;
	};
	public Services(int serid,String sername,int sermoney,String sertype){
		this.serid = serid;
		this.sername = sername;
		this.sermoney = sermoney;
		this.sertype = sertype;
	};
	
}
