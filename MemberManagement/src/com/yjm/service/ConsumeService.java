package com.yjm.service;

import java.util.List;

import com.yjm.dao.ConsumeDAO;
import com.yjm.dao.impl.ConsumeDAOImpl;
import com.yjm.model.Consume;

/**
 * ��������ģ��ҵ���߼���
 * @author lxm
 * @date 2014-3-8 16:38:15
 */
public class ConsumeService {
	
	private ConsumeDAO dao = new ConsumeDAOImpl();
	
	private static ConsumeService ss = null;
	
	private ConsumeService(){}
	
	public static synchronized ConsumeService getInstace(){
		if(ss == null){
			ss = new ConsumeService();
		}
		return ss;
	}
	
	//����
	public void addConsume(Consume c){
		dao.addConsume(c);
	}

	//����
	public void updateConsume(Consume c){
		dao.updateConsume(c);
	}
	
	//��������ID����
	public void updateConsumeByConID(int conid){
		dao.updateConsumeByConID(conid);
	}
	
	//����conID���ĵ绰����
	public void updateConsumePhoneByConID(String conmphone ,int conid){
		dao.updateConsumePhoneByConID(conmphone, conid);
	}
	
	//��ѯȫ��
	public List<Consume> selectAll(){
		return dao.selectAll();
	}
	//��������ID��ѯ
	
	public List<Consume> selectByConID(int conid){
		return dao.selectByConID(conid);
	}
	
	//��������ID��ѯ���ض���
	public Consume selectByConIDReturnConsume(int conid){
		return dao.selectByConIDReturnConsume(conid);
	}
	
	//���յ绰��ѯ
	public List<Consume> selectNoMember(){
		return dao.selectNoMember();
	}
	
	//���յ绰��ѯ
	public List<Consume> selectByPhone(String conmphone){
		return dao.selectByPhone(conmphone);
	}
	
	//����Ա��ID��ѯ
	public List<Consume> selectByConEmID(int conemid){
		return dao.selectByConEmID(conemid);
	}
	
	//����Ա��������ѯ
	public List<Consume> selectByConEmName(String emname){
		return dao.selectByConEmName(emname);
	}
	
	//�������ڲ�ѯ(days)
	public int selectByInDate(String days){
		return dao.selectByinDate(days);
	}
	
	//�������ڲ�ѯ(month)
	public int selectByMonth(int month){
		return dao.selectByMonth(month);
	}
	
	//�������ڲ�ѯ(year)
	public int selectByYear(String year){
		return dao.selectByYear(year);
	}
	
	//�������ڲ�ѯ(between)
	public int selectByTime(String start,String end){
		return dao.selectByTime(start, end);
	}
	
	public int selectCountByServicesNo(int sid) {
		return dao.selectCountByServicesNo(sid);
	}
	
	//����IDɾ�����Ѽ�¼
	public void deleteByID(int conemid){
		dao.deleteByID(conemid);		
	}
}
