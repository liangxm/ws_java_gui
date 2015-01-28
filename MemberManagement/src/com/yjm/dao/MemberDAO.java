package com.yjm.dao;

import java.util.List;

import com.yjm.model.Member;
/**
 * 会员数据持久层接口
 * @author lxm
 * @date 2014-3-8 11:48:16
 */
public interface MemberDAO {

	public void addMember(Member member);
	public void deleteAll();
	public void deleteByID(int mid);
	public void updatemember(Member member);
	public void changeMemberPassByID(String pass,int mid);
	public void updateMoney(int addmoney,String mphone);
	public void earnPoint(int addmoney,String mname);
	public void updatePoint(int addmoney,String mphone);
	public void changeMemberPhoneByID(String phone, int mid);
	public void changeMemberMoneyByID(int money,int mid);
	public List<Member> selectAll();
	public Member selectBymid(int mid);
	public List<Member> selectByBirthday();
	public List<Member> selectByPhone(String phone);
	public int selectByPhoneReturnMoney(String phone);
	public boolean isPhoneExist(String phone);
	public List<Member> selectByName(String mname);
	public void closeSelect();
	
}
