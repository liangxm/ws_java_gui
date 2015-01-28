package com.yjm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yjm.dao.EmployeeDAO;
import com.yjm.jdbc.DBManage;
import com.yjm.model.Employee;
/**
 * 员工数据就持久层实现
 * @author lxm
 * @date 2014-3-8 11:49:42
 */
public class EmployeeDAOImpl implements EmployeeDAO {
	
	private ResultSet rs;
	private String sql;
	private DBManage dbManage = DBManage.getInstance();

	@Override
	public void addEmployee(Employee employee) {
		sql = "insert into  tblEmployee (emname,emsex,emage,emphone,emtel,emaddr,embirth,emcard,emindate,emjob,manage,managepass)values (?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Object> list = new ArrayList<Object>();
		list.add(employee.getEmname());
		list.add(employee.getEmsex());
		list.add(employee.getEmage());
		list.add(employee.getEmphone());
		list.add(employee.getEmtel());
		list.add(employee.getEmaddr());
		list.add(new java.sql.Date(employee.getEmbirth().getTime()));
		list.add(employee.getEmcard());
		list.add(new java.sql.Date(employee.getEmindate().getTime()));
		list.add(employee.getEmjob());
		list.add(employee.getManage());
		list.add(employee.getManagepass());
		dbManage.executeSql(sql, list);
	}

	@Override
	public void updateEmployee(Employee employee) {
		sql ="update tblEmployee set emname=?,emsex = ?, emage = ? ,emphone = ? ,emtel = ?,emaddr = ?,embirth = ?,emcard = ?,emjob = ?,manage = ?, managepass = ? where emid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(employee.getEmname());
		list.add(employee.getEmsex());
		list.add(employee.getEmage());
		list.add(employee.getEmphone());
		list.add(employee.getEmtel());
		list.add(employee.getEmaddr());
		list.add(new java.sql.Date(employee.getEmbirth().getTime()));
		list.add(employee.getEmcard());
		list.add(employee.getEmjob());
		list.add(employee.getManage());
		list.add(employee.getManagepass());
		list.add(employee.getEmid());
		dbManage.executeSql(sql, list);
	}

	@Override
	public void updateManagePass(String manage, String pass) {
		sql ="update tblEmployee set managepass = '"+pass+"' where manage = '"+manage+"'";
		dbManage.executeSql(sql);
	}

	@Override
	public void deleteByEmID(int emid) {
		sql ="delete from tblemployee where emid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(emid);
		dbManage.executeSql(sql, list);
	}

	@Override
	public List<Employee> selectAll() {
		sql ="select * from tblEmployee";
		rs = dbManage.executeQuery(sql, null);
		List<Employee> list = new ArrayList<Employee>();
		try {
			while (rs.next()) {
				Employee em = new Employee();
				em.setEmid(rs.getInt("emid"));
				em.setEmname(rs.getString("emname"));
				em.setEmsex(rs.getString("emsex"));
				em.setEmage(rs.getInt("emage"));
				em.setEmphone(rs.getString("emphone"));
				em.setEmtel(rs.getString("emtel"));
				em.setEmaddr(rs.getString("emaddr"));
				em.setEmbirth(rs.getDate("embirth"));
				em.setEmcard(rs.getString("emcard"));
				em.setEmindate(rs.getDate("emindate"));
				em.setEmjob(rs.getString("emjob"));
				list.add(em);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return list;
	}

	@Override
	public List<Employee> selectManage(String emphone) {
		sql ="select * from tblEmployee where manage=?";
		List<Object> list = new ArrayList<Object>();
		list.add(emphone);
		rs = dbManage.executeQuery(sql, list);
		List<Employee> listmember = new ArrayList<Employee>();
		try {
			while (rs.next()) {
				Employee em = new Employee();
				em.setEmid(rs.getInt("emid"));
				em.setEmname(rs.getString("emname"));
				em.setEmsex(rs.getString("emsex"));
				em.setEmage(rs.getInt("emage"));
				em.setEmphone(rs.getString("emphone"));
				em.setEmtel(rs.getString("emtel"));
				em.setEmaddr(rs.getString("emaddr"));
				em.setEmbirth(rs.getDate("embirth"));
				em.setEmcard(rs.getString("emcard"));
				em.setEmindate(rs.getDate("emindate"));
				em.setEmjob(rs.getString("emjob"));
				listmember.add(em);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return listmember;
	}

	@Override
	public List<Employee> selectByEmPhone(String emphone) {
		sql ="select * from tblEmployee where emphone=?";
		List<Object> list = new ArrayList<Object>();
		list.add(emphone);
		rs = dbManage.executeQuery(sql, list);
		List<Employee> listmember = new ArrayList<Employee>();
		try {
			while (rs.next()) {
				Employee em = new Employee();
				em.setEmid(rs.getInt("emid"));
				em.setEmname(rs.getString("emname"));
				em.setEmsex(rs.getString("emsex"));
				em.setEmage(rs.getInt("emage"));
				em.setEmphone(rs.getString("emphone"));
				em.setEmtel(rs.getString("emtel"));
				em.setEmaddr(rs.getString("emaddr"));
				em.setEmbirth(rs.getDate("embirth"));
				em.setEmcard(rs.getString("emcard"));
				em.setEmindate(rs.getDate("emindate"));
				em.setEmjob(rs.getString("emjob"));
				listmember.add(em);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return listmember;
	}

	@Override
	public Employee selectByEmmIDReturnEM(int emid) {
		sql ="select * from tblEmployee where emid=?";
		List<Object> list = new ArrayList<Object>();
		list.add(emid);
		rs = dbManage.executeQuery(sql, list);
		Employee em = null;
		try {
			while (rs.next()) {
				em = new Employee();
				em.setEmid(rs.getInt("emid"));
				em.setEmname(rs.getString("emname"));
				em.setEmsex(rs.getString("emsex"));
				em.setEmage(rs.getInt("emage"));
				em.setEmphone(rs.getString("emphone"));
				em.setEmtel(rs.getString("emtel"));
				em.setEmaddr(rs.getString("emaddr"));
				em.setEmbirth(rs.getDate("embirth"));
				em.setEmcard(rs.getString("emcard"));
				em.setEmindate(rs.getDate("emindate"));
				em.setEmjob(rs.getString("emjob"));
				em.setManage(rs.getString("manage"));
				em.setManagepass(rs.getString("managepass"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return em;
	}

	@Override
	public List<Employee> selectByName(String emname) {
		sql ="select * from tblEmployee where emname like ?";
		List<Object> list = new ArrayList<Object>();
		list.add("%" + emname + "%");
		rs = dbManage.executeQuery(sql, list);
		List<Employee> listmember = new ArrayList<Employee>();
		try {
			while (rs.next()) {
				Employee em = new Employee();
				em.setEmid(rs.getInt("emid"));
				em.setEmname(rs.getString("emname"));
				em.setEmsex(rs.getString("emsex"));
				em.setEmage(rs.getInt("emage"));
				em.setEmphone(rs.getString("emphone"));
				em.setEmtel(rs.getString("emtel"));
				em.setEmaddr(rs.getString("emaddr"));
				em.setEmbirth(rs.getDate("embirth"));
				em.setEmcard(rs.getString("emcard"));
				em.setEmindate(rs.getDate("emindate"));
				em.setEmjob(rs.getString("emjob"));
				listmember.add(em);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return listmember;
	}

	@Override
	public String selectByManageReturnPass(String manage) {
		sql ="select * from tblEmployee where manage = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(manage);
		rs = dbManage.executeQuery(sql, list);
		String managepass = "";
		try {
			while (rs.next()) {
				managepass = rs.getString("managepass");
				System.out.println("password:" + managepass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return managepass;
	}

	@Override
	public List<Employee> selectByManageReturnUser(String manage) {
		sql ="select * from tblEmployee where manage = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(manage);
		rs = dbManage.executeQuery(sql, list);
		List<Employee> listmember = new ArrayList<Employee>();
		try {
			while (rs.next()) {
				Employee em = new Employee();
				// Member m = new
				// Member(rs.getInt("mid"),rs.getString("mphone"),rs.getString("mname"),rs.getString("msex"),rs.getInt("mage"),rs.getDate("mbirth"),rs.getString("maddr"),rs.getDate("mindate"),rs.getInt("maddmoney"),rs.getInt("mintegral"));
				em.setEmid(rs.getInt("emid"));
				em.setEmname(rs.getString("emname"));
				em.setEmsex(rs.getString("emsex"));
				em.setEmage(rs.getInt("emage"));
				em.setEmphone(rs.getString("emphone"));
				em.setEmtel(rs.getString("emtel"));
				em.setEmaddr(rs.getString("emaddr"));
				em.setEmbirth(rs.getDate("embirth"));
				em.setEmcard(rs.getString("emcard"));
				em.setEmindate(rs.getDate("emindate"));
				em.setEmjob(rs.getString("emjob"));
				em.setEmjob(rs.getString("manage"));
				em.setEmjob(rs.getString("managepass"));
				listmember.add(em);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return listmember;
	}

	@Override
	public Employee selectByNameReturnEmID(String emname) {
		sql ="select * from tblEmployee where emname = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(emname);
		rs = dbManage.executeQuery(sql, list);
		Employee em = null;
		try {
			while (rs.next()) {
				em = new Employee();
				em.setEmid(rs.getInt("emid"));
				em.setEmname(rs.getString("emname"));
				em.setEmsex(rs.getString("emsex"));
				em.setEmage(rs.getInt("emage"));
				em.setEmphone(rs.getString("emphone"));
				em.setEmtel(rs.getString("emtel"));
				em.setEmaddr(rs.getString("emaddr"));
				em.setEmbirth(rs.getDate("embirth"));
				em.setEmcard(rs.getString("emcard"));
				em.setEmindate(rs.getDate("emindate"));
				em.setEmjob(rs.getString("emjob"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return em;
	}

	@Override
	public void closeSelect() {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs = null;
	}


}
