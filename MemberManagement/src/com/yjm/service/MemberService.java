package com.yjm.service;

import java.util.List;

import com.yjm.dao.MemberDAO;
import com.yjm.dao.impl.MemberDAOImpl;
import com.yjm.model.Member;
/**
 * 会员管理模块业务逻辑层
 * @author lxm
 * @date 2014-3-8 18:00:02
 */
public class MemberService {
	
	private MemberDAO dao = new MemberDAOImpl();
	
	private static MemberService ss = null;
	private MemberService(){}
	
	public static synchronized MemberService getInstace(){
		if(ss == null){
			ss = new MemberService();
		}
		return ss;
	}
	
	public void addMember(Member m){
		dao.addMember(m);
	}
	//删除
	public void DeleteALL(){
		dao.deleteAll();
	}
	
	//按照ID删除会员
	public void deleteByID(int mid){
		dao.deleteByID(mid);
	}
	
	//按照编号ID更改全部信息
	public void updateMember(Member m){
		dao.updatemember(m);
	}
	
	//按照编号ID更改密码
	public void changeMemberPassByID(String mpass,int mid){
		dao.changeMemberPassByID(mpass, mid);
	}
	
	//按照会员号更改帐户余额
	public void updateMoney(int maddmoney,String mphone){
		dao.updateMoney(maddmoney, mphone);
	}
	
	//按照会员号更新账户积分
	public void updatePoint(int maddmoney,String mphone){
		dao.updatePoint(maddmoney, mphone);
	}
	
	public void earnPoint(int addmoney, String mname) {
		dao.earnPoint(addmoney, mname);
	}
	
	//按照编号ID更改手机
	public void changeMemberPhoneByID(String mphone,int mid){
		dao.changeMemberPhoneByID(mphone, mid);
	}
	
	//按照编号ID充值
	public void changeMemberMoneyByID(int maddmoney,int mid){
		dao.changeMemberMoneyByID(maddmoney, mid);
	}
	
	//查询全部
	public List<Member> selectAll(){
		return dao.selectAll();
	}
	
	//按照编号mid号码查询
	public Member selectBymid(int mid){
		return dao.selectBymid(mid);
	}
	
	
	//按照手机号码查询
	public List<Member> selectByPhone(String mphone){
		return dao.selectByPhone(mphone);
	}
	
	//查询30内过生日的会员
	public List<Member> selectByBirthday(){
		return dao.selectByBirthday();
	}
	
	//按照手机号码查询帐户余额
	public int selectByPhoneReturnMoney(String mphone){
		return dao.selectByPhoneReturnMoney(mphone);
	}
	
	//查找电话号码是否已被占用
	public boolean isPhoneExist(String mphone){
		return dao.isPhoneExist(mphone);
	}
	
	//按照姓名号码查询
	public List <Member> selectByName(String mname){
		return dao.selectByName(mname);
	}
}






