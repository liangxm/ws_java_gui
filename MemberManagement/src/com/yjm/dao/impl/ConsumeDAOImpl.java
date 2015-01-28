package com.yjm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yjm.dao.ConsumeDAO;
import com.yjm.jdbc.DBManage;
import com.yjm.model.Consume;
import com.yjm.model.Employee;
import com.yjm.service.EmployeeService;
/**
 * 收银数据持久层实现
 * @author lxm
 * @date 2014-3-8 11:49:17
 */
public class ConsumeDAOImpl implements ConsumeDAO {
	
	private ResultSet rs;
	private String strSql;
	private List<Object> list;
	private DBManage dbManage = DBManage.getInstance();

	@Override
	public void addConsume(Consume consume) {
		strSql = "insert into  tblConsume(conmphone,conemid,conserid,condate,conagio,conmoney,conremark) values (?,?,?,?,?,?,?)";
		list = new ArrayList<Object>();
		list.add(consume.getConmphone());
		list.add(consume.getConemid());
		list.add(consume.getConserid());
		list.add(new java.sql.Date(consume.getCondate().getTime()));
		list.add(consume.getConagio());
		list.add(consume.getConmoney());
		list.add(consume.getConremark());
		dbManage.executeSql(strSql, list);
	}

	@Override
	public void updateConsume(Consume consume) {
		strSql = "update tblConsume set conemid =?, conserid =?, condate =?, conagio =?, conmoney =?, conremark =? where conmphone=? ";
		list = new ArrayList<Object>();
		list.add(consume.getConemid());
		list.add(consume.getConserid());
		list.add(new java.sql.Date(consume.getCondate().getTime()));
		list.add(consume.getConagio());
		list.add(consume.getConmoney());
		list.add(consume.getConremark());
		list.add(consume.getConmphone());
		
		dbManage.executeSql(strSql, list);
	}

	@Override
	public void updateConsumeByConID(int conID) {
		strSql = "update tblConsume conremark =? where conid=? ";
		list = new ArrayList<Object>();
		list.add(conID);
		dbManage.executeSql(strSql, list);		
	}

	@Override
	public void updateConsumePhoneByConID(String phone, int conID) {
		strSql = "update tblConsume set conmphone =?  where conid=? ";
		list = new ArrayList<Object>();
		list.add(phone);
		list.add(conID);
		dbManage.executeSql(strSql, list);
	}

	@Override
	public List<Consume> selectAll() {
		strSql = "select * from tblConsume";
		rs = dbManage.executeQuery(strSql, null);
		List<Consume> listmember = new ArrayList<Consume>();
		try {
			while(rs.next()){
				Consume s = new Consume();
				s.setConid(rs.getInt("conid"));
				s.setConmphone(rs.getString("conmphone"));
				s.setConemid(rs.getInt("conemid"));
				s.setConserid(rs.getInt("conserid"));
				s.setCondate(rs.getDate("condate"));
				s.setConagio(rs.getInt("conagio"));
				s.setConmoney(rs.getInt("conmoney"));
				s.setConremark(rs.getString("conremark"));
				
				listmember.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return listmember;
	}

	@Override
	public List<Consume> selectByConID(int conID) {
		strSql = "select * from tblConsume where conid = ?";
		list = new ArrayList<Object>();
		list.add(conID);
		rs = dbManage.executeQuery(strSql, list);
		List <Consume> listmember = new ArrayList<Consume>();
		try {
			while(rs.next()){
				Consume s = new Consume();
				s.setConid(rs.getInt("conid"));
				s.setConmphone(rs.getString("conmphone"));
				s.setConemid(rs.getInt("conemid"));
				s.setConserid(rs.getInt("conserid"));
				s.setCondate(rs.getDate("condate"));
				s.setConagio(rs.getInt("conagio"));
				s.setConmoney(rs.getInt("conmoney"));
				s.setConremark(rs.getString("conremark"));
				
				listmember.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return listmember;
	}

	@Override
	public Consume selectByConIDReturnConsume(int conID) {
		strSql = "select * from tblConsume where conid = ?";
		list = new ArrayList<Object>();
		list.add(conID);
		rs = dbManage.executeQuery(strSql, list);
		Consume s = null ;
		try {
			while(rs.next()){
				s = new Consume();
				s.setConid(rs.getInt("conid"));
				s.setConmphone(rs.getString("conmphone"));
				s.setConemid(rs.getInt("conemid"));
				s.setConserid(rs.getInt("conserid"));
				s.setCondate(rs.getDate("condate"));
				s.setConagio(rs.getInt("conagio"));
				s.setConmoney(rs.getInt("conmoney"));
				s.setConremark(rs.getString("conremark"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return s;
	}

	@Override
	public List<Consume> selectNoMember() {
		strSql = "select * from tblConsume where conmphone <> '000001'";
		rs = dbManage.executeQuery(strSql);
		List <Consume> listmember = new ArrayList<Consume>();
		try {
			while(rs.next()){
				Consume s = new Consume();
				s.setConid(rs.getInt("conid"));
				s.setConmphone(rs.getString("conmphone"));
				s.setConemid(rs.getInt("conemid"));
				s.setConserid(rs.getInt("conserid"));
				s.setCondate(rs.getDate("condate"));
				s.setConagio(rs.getInt("conagio"));
				s.setConmoney(rs.getInt("conmoney"));
				s.setConremark(rs.getString("conremark"));
				
				listmember.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return listmember;
	}

	@Override
	public List<Consume> selectByPhone(String phone) {
		strSql = "select * from tblConsume where conmphone like ?";
		list = new ArrayList<Object>();
		list.add("%" + phone + "%");
		rs = dbManage.executeQuery(strSql, list);
		List <Consume> listmember = new ArrayList<Consume>();
		try {
			while(rs.next()){
				Consume s = new Consume();
				s.setConid(rs.getInt("conid"));
				s.setConmphone(rs.getString("conmphone"));
				s.setConemid(rs.getInt("conemid"));
				s.setConserid(rs.getInt("conserid"));
				s.setCondate(rs.getDate("condate"));
				s.setConagio(rs.getInt("conagio"));
				s.setConmoney(rs.getInt("conmoney"));
				s.setConremark(rs.getString("conremark"));
				
				listmember.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return listmember;
	}

	@Override
	public List<Consume> selectByConEmID(int emID) {
		strSql = "select * from tblConsume where conemid = ?";
		list = new ArrayList<Object>();
		list.add(emID);
		rs = dbManage.executeQuery(strSql, list);
		List <Consume> listmember = new ArrayList<Consume>();
		try {
			while(rs.next()){
				Consume s = new Consume();
				s.setConid(rs.getInt("conid"));
				s.setConmphone(rs.getString("conmphone"));
				s.setConemid(rs.getInt("conemid"));
				s.setConserid(rs.getInt("conserid"));
				s.setCondate(rs.getDate("condate"));
				s.setConagio(rs.getInt("conagio"));
				s.setConmoney(rs.getInt("conmoney"));
				s.setConremark(rs.getString("conremark"));
				
				listmember.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return listmember;
	}

	@Override
	public List<Consume> selectByConEmName(String conName) {
		Employee em = EmployeeService.getInstace().selectByNameReturnEmID(conName);
		int conemid = em!=null?1:0;
		strSql = "select * from tblConsume where conemid = ?";
		list = new ArrayList<Object>();
		list.add(conemid);
		rs = dbManage.executeQuery(strSql, list);
		List <Consume> listmember = new ArrayList<Consume>();
		try {
			while(rs.next()){
				Consume s = new Consume();
				s.setConid(rs.getInt("conid"));
				s.setConmphone(rs.getString("conmphone"));
				s.setConemid(rs.getInt("conemid"));
				s.setConserid(rs.getInt("conserid"));
				s.setCondate(rs.getDate("condate"));
				s.setConagio(rs.getInt("conagio"));
				s.setConmoney(rs.getInt("conmoney"));
				s.setConremark(rs.getString("conremark"));
				
				listmember.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return listmember;
	}

	@Override
	public int selectByinDate(String inDate) {
		strSql = "select SUM(conmoney) as total from tblConsume where condate = ?";
		list = new ArrayList<Object>();
		list.add(inDate);
		rs = dbManage.executeQuery(strSql, list);
		int total = 0;
		try {
			while(rs.next()){
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return total;
	}

	@Override
	public int selectByMonth(int month) {
		strSql = "select SUM(conmoney) as total from tblConsume where month(condate) = ?";
		list = new ArrayList<Object>();
		list.add(month);
		rs = dbManage.executeQuery(strSql, list);
		int total = 0;
		try {
			while(rs.next()){
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return total;
	}

	@Override
	public int selectByYear(String year) {
		strSql = "select SUM(conmoney) as total from tblConsume where year(condate) = ?";
		list = new ArrayList<Object>();
		list.add(year);
		rs = dbManage.executeQuery(strSql, list);
		int total = 0;
		try {
			while(rs.next()){
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return total;
	}

	@Override
	public int selectByTime(String start, String end) {
		strSql = "select SUM(conmoney) as total from tblConsume where condate between ? and ?";
		list = new ArrayList<Object>();
		list.add(start);
		list.add(end);
		rs = dbManage.executeQuery(strSql, list);
		int total = 0;
		try {
			while(rs.next()){
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return total;
	}
	
	@Override
	public int selectCountByServicesNo(int sid) {
		strSql = "select count(*) as total from tblConsume where conserid = ?";
		list = new ArrayList<Object>();
		list.add(sid);
		rs = dbManage.executeQuery(strSql, list);
		int total = 0;
		try {
			while(rs.next()){
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return total;
	}

	@Override
	public void deleteByID(int conID) {
		String sql = "delete from tblConsume where conemid = ?";
		list = new ArrayList<Object>();
		list.add(conID);
		dbManage.executeSql(sql, list);	
	}

	@Override
	public void closeSelect() {
		try {
			if(rs !=null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs = null;
	}

}
