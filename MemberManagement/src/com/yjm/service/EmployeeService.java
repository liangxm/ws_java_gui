package com.yjm.service;

import java.util.List;

import com.yjm.dao.EmployeeDAO;
import com.yjm.dao.impl.EmployeeDAOImpl;
import com.yjm.model.Employee;
/**
 * 会员管理模块业务逻辑层
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

	// 更改
	public void updateEmployee(Employee em) {
		dao.updateEmployee(em);
	}
	
	// 按管理员名称改密码
	public void updateManagePass(String manage,String pass) {
		dao.updateManagePass(manage, pass);
	}

	// 按照ID删除记录
	public void deleteByEmID(int emid) {
		dao.deleteByEmID(emid);
	}

	// 查询全部
	public List<Employee> selectAll() {
		return dao.selectAll();
	}

	// 按照电话号码查询
	public List<Employee> selectManage(String emphone) {
		return dao.selectManage(emphone);
	}

	// 按照电话号码查询
	public List<Employee> selectByEmPhone(String emphone) {
		return dao.selectByEmPhone(emphone);
	}

	// 按照ID查询
	public Employee selectByEmIDReturnEM(int emid) {
		return dao.selectByEmmIDReturnEM(emid);
	}

	// 按照姓名号码查询
	public List<Employee> selectByName(String mname) {
		return dao.selectByName(mname);
	}

	// 按照管理用户名查询管理密码
	public String selectByManageReturnPass(String manage) {
		return dao.selectByManageReturnPass(manage);
	}

	// 按照管理用户名查询是否存在该管理用户
	public List<Employee> selectByManageReturnUser(String manage) {
		return dao.selectByManageReturnUser(manage);
	}

	// 按照姓名查询员工ID
	public Employee selectByNameReturnEmID(String emname) {
		return dao.selectByNameReturnEmID(emname);
	}
}
