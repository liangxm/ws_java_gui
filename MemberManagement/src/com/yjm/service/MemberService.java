package com.yjm.service;

import java.util.List;

import com.yjm.dao.MemberDAO;
import com.yjm.dao.impl.MemberDAOImpl;
import com.yjm.model.Member;
/**
 * ��Ա����ģ��ҵ���߼���
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
	//ɾ��
	public void DeleteALL(){
		dao.deleteAll();
	}
	
	//����IDɾ����Ա
	public void deleteByID(int mid){
		dao.deleteByID(mid);
	}
	
	//���ձ��ID����ȫ����Ϣ
	public void updateMember(Member m){
		dao.updatemember(m);
	}
	
	//���ձ��ID��������
	public void changeMemberPassByID(String mpass,int mid){
		dao.changeMemberPassByID(mpass, mid);
	}
	
	//���ջ�Ա�Ÿ����ʻ����
	public void updateMoney(int maddmoney,String mphone){
		dao.updateMoney(maddmoney, mphone);
	}
	
	//���ջ�Ա�Ÿ����˻�����
	public void updatePoint(int maddmoney,String mphone){
		dao.updatePoint(maddmoney, mphone);
	}
	
	public void earnPoint(int addmoney, String mname) {
		dao.earnPoint(addmoney, mname);
	}
	
	//���ձ��ID�����ֻ�
	public void changeMemberPhoneByID(String mphone,int mid){
		dao.changeMemberPhoneByID(mphone, mid);
	}
	
	//���ձ��ID��ֵ
	public void changeMemberMoneyByID(int maddmoney,int mid){
		dao.changeMemberMoneyByID(maddmoney, mid);
	}
	
	//��ѯȫ��
	public List<Member> selectAll(){
		return dao.selectAll();
	}
	
	//���ձ��mid�����ѯ
	public Member selectBymid(int mid){
		return dao.selectBymid(mid);
	}
	
	
	//�����ֻ������ѯ
	public List<Member> selectByPhone(String mphone){
		return dao.selectByPhone(mphone);
	}
	
	//��ѯ30�ڹ����յĻ�Ա
	public List<Member> selectByBirthday(){
		return dao.selectByBirthday();
	}
	
	//�����ֻ������ѯ�ʻ����
	public int selectByPhoneReturnMoney(String mphone){
		return dao.selectByPhoneReturnMoney(mphone);
	}
	
	//���ҵ绰�����Ƿ��ѱ�ռ��
	public boolean isPhoneExist(String mphone){
		return dao.isPhoneExist(mphone);
	}
	
	//�������������ѯ
	public List <Member> selectByName(String mname){
		return dao.selectByName(mname);
	}
}






