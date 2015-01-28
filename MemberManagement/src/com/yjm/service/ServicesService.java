package com.yjm.service;

import java.util.List;

import com.yjm.dao.ServicesDAO;
import com.yjm.dao.impl.ServicesDAOImpl;
import com.yjm.model.Services;

/**
 * ������Ŀ����ҵ���߼���
 * @author lxm
 * @date 2014-3-8 18:27:04
 */
public class ServicesService {
	
	private ServicesDAO dao = new ServicesDAOImpl();
	
	private static ServicesService ss = null;
	private ServicesService(){}
	
	public static synchronized ServicesService getInstace(){
		if(ss == null){
			ss = new ServicesService();
		}
		return ss;
	}
	
	public void addService(Services s){
		dao.addService(s);
	}
	
	//����IDɾ���������
	public void deleteByID(int serid){
		dao.deleteByID(serid);
	}

	//����
	public void updateServices(Services s){
		dao.updateServices(s);
	}
	//��ѯȫ��
	public List<Services> selectAll(){
		return dao.selectAll();
	}
	
	//����ID��ѯȫ������SERVICE����
	public Services selectByIDReturnServices(int serid){
		return dao.selectByIDReturnServices(serid);
	}
	
	//����ID��ѯȫ������SERVICE����
	public List<Services> selectByServicesType(String sertype){
		return dao.selectByServicesType(sertype);
	}
	
	//�������������ѯ
	public List<Services> selectByName(String sername){
		return dao.selectByName(sername);
	}
	
	//��ѯ������Ŀ�ļ۸�
	public double selectByNameReturnPrice(String sername){
		return dao.selectByNameReturnPrice(sername);
	}
	
	//��ѯ������Ŀ�ļ۸�
	public double selectByNamePrice(String sername){
		return dao.selectByNamePrice(sername);
	}
	
	//��ѯ������Ŀ�ļ۸�
	public int selectByNameNo(String sername){
		return dao.selectByNameNo(sername);
	}
	
	//���շ������ֲ���ID
	public int selectByNameReturnID(String sname){
		return dao.selectByNameReturnID(sname);
	}
}






