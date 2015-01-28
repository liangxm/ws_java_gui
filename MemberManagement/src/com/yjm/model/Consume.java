package com.yjm.model;

import java.util.Date;
/**
 * 消费明细实体类
 * @author lxm
 * @version 2014-3-1 10:59:41
 */
public class Consume {
	
	public int getConid() {
		return conid;
	}
	public void setConid(int conid) {
		this.conid = conid;
	}
	public String getConmphone() {
		return conmphone;
	}
	public void setConmphone(String conmphone) {
		this.conmphone = conmphone;
	}
	public int getConemid() {
		return conemid;
	}
	public void setConemid(int conemid) {
		this.conemid = conemid;
	}
	public int getConserid() {
		return conserid;
	}
	public void setConserid(int conserid) {
		this.conserid = conserid;
	}
	public Date getCondate() {
		return condate;
	}
	public void setCondate(Date condate) {
		this.condate = condate;
	}
	public int getConagio() {
		return conagio;
	}
	public void setConagio(int conagio) {
		this.conagio = conagio;
	}
	public double getConmoney() {
		return conmoney;
	}
	public void setConmoney(double conmoney) {
		this.conmoney = conmoney;
	}
	public String getConremark() {
		return conremark;
	}
	public void setConremark(String conremark) {
		this.conremark = conremark;
	}

	//消费编号 
	private int conid;
	//会员编号
	private String conmphone;
	//员工编号
	private int conemid;
	//服务编号
	private int conserid;
	//消费日期
	private Date condate;
	//折扣
	private int conagio;
	//消费金额
	private double conmoney;
	//备注
	private String conremark;
	
	
	
	public Consume(){};
	Consume(int conid,String conmphone,int conemid,int conserid,Date condate,int conagio,double conmoney,String conremark){
		this.conid = conid;
		this.conmphone = conmphone;
		this.conemid = conemid;
		this.conserid = conserid;
		this.condate = condate;
		this.conagio = conagio;
		this.conmoney = conmoney;
		this.conremark = conremark;
	};
	
	public Consume(String conmphone,int conemid,int conserid,Date condate,int conagio,double conmoney,String conremark){
		this.conmphone = conmphone;
		this.conemid = conemid;
		this.conserid = conserid;
		this.condate = condate;
		this.conagio = conagio;
		this.conmoney = conmoney;
		this.conremark = conremark;
	};
	
	Consume(String conmphone,int conemid,int conserid,Date condate,int conagio,int conmoney){
		this.conmphone = conmphone;
		this.conemid = conemid;
		this.conserid = conserid;
		this.condate = condate;
		this.conagio = conagio;
		this.conmoney = conmoney;
	};


}
