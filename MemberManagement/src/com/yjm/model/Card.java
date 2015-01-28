package com.yjm.model;

import java.util.Date;

/**
 * 会员卡实体对象
 * 
 * @author lxm
 * @date 2014-3-10 21:15:43
 */
public class Card {

	private int cardid;
	private String cardnumber;
	private String cardtype;
	private String cardowner;
	private Date activedate;
	private Date expirydate;
	private double cardbalace;
	private Boolean active;
	private Boolean block;
	
	public Card(){}
	
	public int getCardid() {
		return cardid;
	}

	public void setCardid(int cardid) {
		this.cardid = cardid;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getCardowner() {
		return cardowner;
	}

	public void setCardowner(String cardowner) {
		this.cardowner = cardowner;
	}

	public Date getActivedate() {
		return activedate;
	}

	public void setActivedate(Date activedate) {
		this.activedate = activedate;
	}

	public Date getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}

	public double getCardbalace() {
		return cardbalace;
	}

	public void setCardbalace(double cardbalace) {
		this.cardbalace = cardbalace;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getBlock() {
		return block;
	}

	public void setBlock(Boolean block) {
		this.block = block;
	}

}
