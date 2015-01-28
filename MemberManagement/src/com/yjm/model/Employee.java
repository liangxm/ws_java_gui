package com.yjm.model;

import java.util.Date;

public class Employee {
	public int getEmid() {
		return emid;
	}
	public void setEmid(int emid) {
		this.emid = emid;
	}
	public String getEmname() {
		return emname;
	}
	public void setEmname(String emname) {
		this.emname = emname;
	}
	public String getEmsex() {
		return emsex;
	}
	public void setEmsex(String emsex) {
		this.emsex = emsex;
	}
	public int getEmage() {
		return emage;
	}
	public void setEmage(int emage) {
		this.emage = emage;
	}
	public String getEmphone() {
		return emphone;
	}
	public void setEmphone(String emphone) {
		this.emphone = emphone;
	}
	public String getEmtel() {
		return emtel;
	}
	public void setEmtel(String emtel) {
		this.emtel = emtel;
	}
	public String getEmaddr() {
		return emaddr;
	}
	public void setEmaddr(String emaddr) {
		this.emaddr = emaddr;
	}
	public Date getEmbirth() {
		return embirth;
	}
	public void setEmbirth(Date embirth) {
		this.embirth = embirth;
	}
	public String getEmcard() {
		return emcard;
	}
	public void setEmcard(String emcard) {
		this.emcard = emcard;
	}
	public Date getEmindate() {
		return emindate;
	}
	public void setEmindate(Date emindate) {
		this.emindate = emindate;
	}
	public String getEmjob() {
		return emjob;
	}
	public void setEmjob(String emjob) {
		this.emjob = emjob;
	}
	public String getManage() {
		return manage;
	}
	public void setManage(String manage) {
		this.manage = manage;
	}
	
	public String getManagepass() {
		return managepass;
	}
	public void setManagepass(String managepass) {
		this.managepass = managepass;
	}
	
	private int emid;
	private String emname;
	private String emsex;
	private int emage;
	private String emphone;
	private String emtel;
	private String emaddr;
	private Date embirth;
	private String emcard;
	private Date emindate;
	private String emjob;
	private String manage;
	private String managepass;

	public Employee(){};
	public Employee(int emid,String emname,String emsex,int emage,String emphone,String emtel,String emaddr,Date embirth,String emcard,Date emindate,String emjob){
		this.emid =emid;
		this.emname =emname;
		this.emsex =emsex;
		this.emage = emage;
		this.emphone =emphone;
		this.emtel =emtel;
		this.emaddr =emaddr;
		this.embirth =embirth;
		this.emcard =emcard;
		this.emindate =emindate;
		this.emjob =emjob;
	};
	
	public Employee(String emname,String emsex,int emage,String emphone,String emtel,String emaddr,Date embirth,String emcard,Date emindate,String emjob){
		this.emname =emname;
		this.emsex =emsex;
		this.emage = emage;
		this.emphone =emphone;
		this.emtel =emtel;
		this.emaddr =emaddr;
		this.embirth =embirth;
		this.emcard =emcard;
		this.emindate =emindate;
		this.emjob =emjob;
	};
	
	public Employee(String emname,String emsex,int emage,String emphone,String emtel,String emaddr,Date embirth,String emcard,Date emindate,String emjob,String manage,String managepass){
		this.emname =emname;
		this.emsex =emsex;
		this.emage = emage;
		this.emphone =emphone;
		this.emtel =emtel;
		this.emaddr =emaddr;
		this.embirth =embirth;
		this.emcard =emcard;
		this.emindate =emindate;
		this.emjob =emjob;
		this.manage  = manage;
		this.managepass = managepass;
	};
	
	public Employee( int emid,String emname,String emsex,int emage,String emphone,String emtel,
			String emaddr,Date embirth,String emcard,Date emindate,String emjob,String manage,String managepass){
		this.emid =emid;
		this.emname =emname;
		this.emsex =emsex;
		this.emage = emage;
		this.emphone =emphone;
		this.emtel =emtel;
		this.emaddr =emaddr;
		this.embirth =embirth;
		this.emcard =emcard;
		this.emindate =emindate;
		this.emjob =emjob;
		this.manage = manage;
		this.managepass = managepass;
	};
	
	public Employee( int emid,String emname,String emsex,int emage,String emphone,String emtel,
			String emaddr,Date embirth,String emcard,String emjob,String manage,String managepass){
		this.emid =emid;
		this.emname =emname;
		this.emsex =emsex;
		this.emage = emage;
		this.emphone =emphone;
		this.emtel =emtel;
		this.emaddr =emaddr;
		this.embirth =embirth;
		this.emcard =emcard;
		this.emjob =emjob;
		this.manage = manage;
		this.managepass = managepass;
	};
	
	public Employee(int emid,String emname,String emsex,int emage,String emphone,String emtel,
			String emaddr,Date embirth,String emcard,String emjob){
		this.emid =emid;
		this.emname =emname;
		this.emsex =emsex;
		this.emage = emage;
		this.emphone =emphone;
		this.emtel =emtel;
		this.emaddr =emaddr;
		this.embirth =embirth;
		this.emcard =emcard;
		this.emjob =emjob;
	};
	
	
}
