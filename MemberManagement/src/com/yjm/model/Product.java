package com.yjm.model;

import java.sql.Timestamp;
import java.sql.Date;

/**
 * 商品信息表
 * 
 * @author lxm
 * @version 2014-2-26 21:02:13
 */
public class Product {

	private int pid;
	private String prid;
	private String pname;
	private int pcount;
	private double pperprice;
	private Date penterdate;
	private Timestamp pindate;
	private String pfrom;
	private String pby;
	private Date pexpiry;
	
	public Product(){}

	public Product(int pid, String prid, String pname, int pcount,
			double pperprice, Date penterdate, Timestamp pindate,
			String pfrom, String pby, Date pexpiry) {
		super();
		this.pid = pid;
		this.prid = prid;
		this.pname = pname;
		this.pcount = pcount;
		this.pperprice = pperprice;
		this.penterdate = penterdate;
		this.pindate = pindate;
		this.pfrom = pfrom;
		this.pby = pby;
		this.pexpiry = pexpiry;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPrid() {
		return prid;
	}

	public void setPrid(String prid) {
		this.prid = prid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPcount() {
		return pcount;
	}

	public void setPcount(int pcount) {
		this.pcount = pcount;
	}

	public double getPperprice() {
		return pperprice;
	}

	public void setPperprice(double pperprice) {
		this.pperprice = pperprice;
	}

	public Date getPenterdate() {
		return penterdate;
	}

	public void setPenterdate(Date penterdate) {
		this.penterdate = penterdate;
	}

	public Timestamp getPindate() {
		return pindate;
	}

	public void setPindate(Timestamp pindate) {
		this.pindate = pindate;
	}

	public String getPfrom() {
		return pfrom;
	}

	public void setPfrom(String pfrom) {
		this.pfrom = pfrom;
	}

	public String getPby() {
		return pby;
	}

	public void setPby(String pby) {
		this.pby = pby;
	}

	public Date getPexpiry() {
		return pexpiry;
	}

	public void setPexpiry(Date pexpiry) {
		this.pexpiry = pexpiry;
	}

}
