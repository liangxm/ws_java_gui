package com.yjm.service;

import java.util.List;

import com.yjm.dao.EmployeeDAO;
import com.yjm.dao.impl.EmployeeDAOImpl;
import com.yjm.model.Employee;
/**
 * ��Ա����ģ��ҵ���߼���
 * @author lxm
 * @date 2014-3-8 17:05:05
 */
public class EmployeeService {
	
	private EmployeeDAO dao = new EmployeeDAOImpl();
	
	private static EmployeeService ss = null;
	private EmployeeService(){}
	
	public static synchronized EmployeeService getInstace(){
		if(ss == null){
			ss = new EmployeeService();
		}
		return ss;
	}

	public void addEmployee(Employee em) {
		dao.addEmployee(em);
	}

	// ����
	public void updateEmployee(Employee em) {
		dao.updateEmployee(em);
	}
	
	// ������Ա���Ƹ�����
	public void updateManagePass(String manage,String pass) {
		dao.updateManagePass(manage, pass);
	}

	// ����IDɾ����¼
	public void deleteByEmID(int emid) {
		dao.deleteByEmID(emid);
	}

	// ��ѯȫ��
	public List<Employee> selectAll() {
		return dao.selectAll();
	}

	// ���յ绰�����ѯ
	public List<Employee> selectManage(String emphone) {
		return dao.selectManage(emphone);
	}

	// ���յ绰�����ѯ
	public List<Employee> selectByEmPhone(String emphone) {
		return dao.selectByEmPhone(emphone);
	}

	// ����ID��ѯ
	public Employee selectByEmIDReturnEM(int emid) {
		return dao.selectByEmmIDReturnEM(emid);
	}

	// �������������ѯ
	public List<Employee> selectByName(String mname) {
		return dao.selectByName(mname);
	}

	// ���չ����û�����ѯ��������
	public String selectByManageReturnPass(String manage) {
		return dao.selectByManageReturnPass(manage);
	}

	// ���չ����û�����ѯ�Ƿ���ڸù����û�
	public List<Employee> selectByManageReturnUser(String manage) {
		return dao.selectByManageReturnUser(manage);
	}

	// ����������ѯԱ��ID
	public Employee selectByNameReturnEmID(String emname) {
		return dao.selectByNameReturnEmID(emname);
	}
}
