package com.yjm.model;

/**
 * 卡类型实体类
 * 
 * @author lxm
 * @date 2014-3-12 21:55:16
 */
public class CardType {

	private int typeid;
	private String typename;
	private int typediscount;
	private int typestartamt;
	
	public CardType(){}
	
	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public int getTypediscount() {
		return typediscount;
	}

	public void setTypediscount(int typediscount) {
		this.typediscount = typediscount;
	}

	public int getTypestartamt() {
		return typestartamt;
	}

	public void setTypestartamt(int typestartamt) {
		this.typestartamt = typestartamt;
	}
	
}