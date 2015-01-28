package com.yjm.model;

import java.util.Date;

public class Member {
	public int mid;
	private String mphone;
	private String mname;
	private String msex;
	private int mage;
	private Date mbirth;
	private String maddr;
	private Date mindate;
	private int maddmoney;
	private int mtotalmoney;
	private int mintegral;
	private String mpass;
	
	

	public Member() {}

	public Member(String mpass,String mphone,String mname,String msex,int mage,Date mbirth,String maddr,Date mindate,int maddmoney,int mtotalmoney,int mintegral) {
		this.mpass = mpass;
		this.mphone = mphone;
		this.mname = mname;
		this.msex = msex;
		this.mage = mage;
		this.mbirth = mbirth;
		this.maddr = maddr;
		this.mindate = mindate;
		this.maddmoney = maddmoney;
		this.mtotalmoney = mtotalmoney;
		this.mintegral = mintegral;
	}
	public Member(int mid,String mpass,String mphone,String mname,String msex,int mage,Date mbirth,String maddr,Date mindate,int maddmoney,int mtotalmoney,int mintegral) {
		this.mid = mid;
		this.mpass = mpass;
		this.mphone = mphone;
		this.mname = mname;
		this.msex = msex;
		this.mage = mage;
		this.mbirth = mbirth;
		this.maddr = maddr;
		this.mindate = mindate;
		this.maddmoney = maddmoney;
		this.mtotalmoney = mtotalmoney;
		this.mintegral = mintegral;
	}
	
	public Member(int mid,String mphone,String mname,String msex,int mage,Date mbirth,String maddr,Date mindate,int maddmoney,int mintegral) {
		this.mid = mid;
		this.mphone = mphone;
		this.mname = mname;
		this.msex = msex;
		this.mage = mage;
		this.mbirth = mbirth;
		this.maddr = maddr;
		this.mindate = mindate;
		this.maddmoney = maddmoney;
		this.mintegral = mintegral;
	}
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMsex() {
		return msex;
	}
	public void setMsex(String msex) {
		this.msex = msex;
	}
	public int getMage() {
		return mage;
	}
	public void setMage(int mage) {
		this.mage = mage;
	}
	public Date getMbirth() {
		return mbirth;
	}
	public void setMbirth(Date mbirth) {
		this.mbirth = mbirth;
	}
	public String getMaddr() {
		return maddr;
	}
	public void setMaddr(String maddr) {
		this.maddr = maddr;
	}
	public Date getMindate() {
		return mindate;
	}
	public void setMindate(Date mindate) {
		this.mindate = mindate;
	}
	public int getMaddmoney() {
		return maddmoney;
	}
	public void setMaddmoney(int maddmoney) {
		this.maddmoney = maddmoney;
	}
	public int getMtotalmoney() {
		return mtotalmoney;
	}
	public void setMtotalmoney(int mtotalmoney) {
		this.mtotalmoney = mtotalmoney;
	}
	public int getMintegral() {
		return mintegral;
	}
	public void setMintegral(int mintegral) {
		this.mintegral = mintegral;
	}
	public String getMpass() {
		return mpass;
	}

	public void setMpass(String mpass) {
		this.mpass = mpass;
	}

}
