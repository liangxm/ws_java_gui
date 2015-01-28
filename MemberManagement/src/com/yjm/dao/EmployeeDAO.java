package com.yjm.dao;

import java.util.List;

import com.yjm.model.Employee;
/**
 * 员工数据持久层接口
 * @author lxm
 * @date 2014-3-8 11:48:35
 */
public interface EmployeeDAO {

	public void addEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public void updateManagePass(String manage,String pass);
	public void deleteByEmID(int emid);
	public List<Employee> selectAll();
	public List<Employee> selectManage(String emphone);
	public List<Employee> selectByEmPhone(String emphone);
	public Employee selectByEmmIDReturnEM(int emid);
	public List<Employee> selectByName(String emname);
	public String selectByManageReturnPass(String manage);
	public List<Employee> selectByManageReturnUser(String manage);
	public Employee selectByNameReturnEmID(String emname);
	public void closeSelect();
	
}
