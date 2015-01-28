package com.yjm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yjm.dao.MemberDAO;
import com.yjm.jdbc.DBManage;
import com.yjm.model.Member;

/**
 * 会员数据持久层实现
 * 
 * @author lxm
 * @date 2014-3-8 11:50:04
 */
public class MemberDAOImpl implements MemberDAO {

	private ResultSet rs;
	private String sql;
	private DBManage dbManage = DBManage.getInstance();

	@Override
	public void addMember(Member member) {
		sql = "insert into  tblMember (mpass,mphone,mname,msex,mage,mbirth,maddr,mindate,maddmoney,mintegral) values (?,?,?,?,?,?,?,?,?,?)";
		List<Object> list = new ArrayList<Object>();
		list.add(member.getMpass());
		list.add(member.getMphone());
		list.add(member.getMname());
		list.add(member.getMsex());
		list.add(member.getMage());
		list.add(new java.sql.Date(member.getMbirth().getTime()));
		list.add(member.getMaddr());
		list.add(new java.sql.Date(member.getMindate().getTime()));
		list.add(member.getMaddmoney());
		list.add(member.getMintegral());
		dbManage.executeSql(sql, list);
	}

	@Override
	public void deleteAll() {
		String sqldelConsume = "delete from tblConsume"; // 初始化数据
		String sqldelEmployee = "delete from tblEmployee"; // 初始化数据
		String sqldelMember = "delete from tblMember"; // 初始化数据
		String sqldelServices = "delete from tblServices"; // 初始化数据
		dbManage.executeSql(sqldelConsume, null);
		dbManage.executeSql(sqldelEmployee, null);
		dbManage.executeSql(sqldelMember, null);
		dbManage.executeSql(sqldelServices, null);
	}

	@Override
	public void deleteByID(int mid) {
		sql = "delete from tblmember where mid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(mid);
		dbManage.executeSql(sql, list);
	}

	@Override
	public void updatemember(Member member) {
		sql = "update tblmember set mphone=?,mname = ?, msex = ?,mage = ?,mbirth = ?,maddr = ?,mindate = ? where mid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(member.getMphone());
		list.add(member.getMname());
		list.add(member.getMsex());
		list.add(member.getMage());
		list.add(new java.sql.Date(member.getMbirth().getTime()));
		list.add(member.getMaddr());
		list.add(new java.sql.Date(member.getMindate().getTime()));
		list.add(member.getMid());
		dbManage.executeSql(sql, list);
	}

	@Override
	public void changeMemberPassByID(String pass, int mid) {
		sql = "update tblmember set mpass=? where mid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(pass);
		list.add(mid);
		dbManage.executeSql(sql, list);
	}

	@Override
	public void updateMoney(int maddmoney, String mphone) {
		sql = "update tblmember set mtotalmoney = mtotalmoney + ?,mintegral = mintegral + ? where mphone = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(maddmoney);
		list.add(maddmoney);
		list.add(mphone);
		dbManage.executeSql(sql, list);
	}
	
	@Override
	public void updatePoint(int addmoney, String mphone) {
		sql = "update tblmember set mtotalmoney = mtotalmoney + ?,mintegral = mintegral + ? where mphone = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(addmoney);
		list.add(addmoney);
		list.add(mphone);
		dbManage.executeSql(sql, list);
	}
	
	@Override
	public void earnPoint(int addmoney, String mname) {
		sql = "update tblmember set mtotalmoney = mtotalmoney + ?,mintegral = mintegral + ? where mname = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(addmoney);
		list.add(addmoney);
		list.add(mname);
		dbManage.executeSql(sql, list);
	}
	
	@Override
	public void changeMemberPhoneByID(String phone, int mid) {
		sql = "update tblmember set mphone=? where mid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(phone);
		list.add(mid);
		dbManage.executeSql(sql, list);
	}

	@Override
	public void changeMemberMoneyByID(int money, int mid) {
		sql = "update tblmember set maddmoney= maddmoney + ? where mid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(money);
		list.add(mid);
		dbManage.executeSql(sql, list);
	}

	@Override
	public List<Member> selectAll() {
		sql = "select * from tblMember";
		rs = dbManage.executeQuery(sql, null);
		List<Member> list = new ArrayList<Member>();
		try {
			while (rs.next()) {
				Member m = new Member();
				m.setMid(rs.getInt("mid"));
				m.setMpass(rs.getString("mpass"));
				m.setMphone(rs.getString("mphone"));
				m.setMname(rs.getString("mname"));
				m.setMsex(rs.getString("msex"));
				m.setMage(rs.getInt("mage"));
				m.setMbirth(rs.getDate("mbirth"));
				m.setMaddr(rs.getString("maddr"));
				m.setMindate(rs.getDate("mindate"));
				m.setMaddmoney(rs.getInt("maddmoney"));
				m.setMtotalmoney(rs.getInt("mtotalmoney"));
				m.setMintegral(rs.getInt("mintegral"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return list;
	}

	@Override
	public Member selectBymid(int mid) {
		String strSql = "select * from tblMember where mid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(mid);
		Member m = null;
		rs = dbManage.executeQuery(strSql, list);
		try {
			while (rs.next()) {
				m = new Member(rs.getInt("mid"), rs.getString("mpass"),
						rs.getString("mphone"), rs.getString("mname"),
						rs.getString("msex"), rs.getInt("mage"),
						rs.getDate("mbirth"), rs.getString("maddr"),
						rs.getDate("mindate"), rs.getInt("maddmoney"),
						rs.getInt("mtotalmoney"), rs.getInt("mintegral"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return m;
	}

	@Override
	public List<Member> selectByPhone(String phone) {
		sql = "select * from tblMember where mphone like ?";
		List<Object> list = new ArrayList<Object>();
		list.add("%" + phone + "%");
		rs = dbManage.executeQuery(sql, list);
		List<Member> listmember = new ArrayList<Member>();
		try {
			while (rs.next()) {
				Member m = new Member(rs.getInt("mid"), rs.getString("mpass"),
						rs.getString("mphone"), rs.getString("mname"),
						rs.getString("msex"), rs.getInt("mage"),
						rs.getDate("mbirth"), rs.getString("maddr"),
						rs.getDate("mindate"), rs.getInt("maddmoney"),
						rs.getInt("mtotalmoney"), rs.getInt("mintegral"));
				listmember.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return listmember;
	}

	@Override
	public int selectByPhoneReturnMoney(String phone) {
		sql = "select * from tblMember where mphone = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(phone);
		rs = dbManage.executeQuery(sql, list);
		int formerMoney = 0;
		try {
			while (rs.next()) {
				formerMoney = rs.getInt("maddmoney");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return formerMoney;
	}

	@Override
	public boolean isPhoneExist(String phone) {
		sql = "select * from tblMember where mphone like ?";
		List<Object> list = new ArrayList<Object>();
		list.add("%" + phone + "%");
		rs = dbManage.executeQuery(sql, list);
		try {
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return false;
	}

	@Override
	public List<Member> selectByName(String mname) {
		sql = "select * from tblMember where mname like ?";
		List<Object> list = new ArrayList<Object>();
		list.add("%" + mname + "%");
		rs = dbManage.executeQuery(sql, list);
		List<Member> listmember = new ArrayList<Member>();
		try {
			while (rs.next()) {
				Member m = new Member(rs.getInt("mid"), rs.getString("mpass"),
						rs.getString("mphone"), rs.getString("mname"),
						rs.getString("msex"), rs.getInt("mage"),
						rs.getDate("mbirth"), rs.getString("maddr"),
						rs.getDate("mindate"), rs.getInt("maddmoney"),
						rs.getInt("mtotalmoney"), rs.getInt("mintegral"));
				listmember.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return listmember;
	}
	
	@Override
	public List<Member> selectByBirthday() {
		sql = "SELECT * from tblmember where dayofyear(curdate())  - dayofyear(mbirth) between 0 and 30";
		rs = dbManage.executeQuery(sql);
		List<Member> listmember = new ArrayList<Member>();
		try {
			while (rs.next()) {
				Member m = new Member(rs.getInt("mid"), rs.getString("mpass"),
						rs.getString("mphone"), rs.getString("mname"),
						rs.getString("msex"), rs.getInt("mage"),
						rs.getDate("mbirth"), rs.getString("maddr"),
						rs.getDate("mindate"), rs.getInt("maddmoney"),
						rs.getInt("mtotalmoney"), rs.getInt("mintegral"));
				listmember.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return listmember;
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
