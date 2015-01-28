package com.yjm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yjm.dao.ServicesDAO;
import com.yjm.jdbc.DBManage;
import com.yjm.model.Services;
/**
 * 服务项目数据持久层实现
 * @author lxm
 * @date 2014-3-8 11:51:03
 */
public class ServicesDAOImpl implements ServicesDAO {
	
	private ResultSet rs;
	private String sql;
	private DBManage dbManage = DBManage.getInstance();

	@Override
	public void addService(Services services) {
		sql = "insert into  tblservices(sername,sermoney,sertype) values (?,?,?)";
		List<Object> list = new ArrayList<Object>();
		list.add(services.getSername());
		list.add(services.getSermoney());
		list.add(services.getSertype());
		dbManage.executeSql(sql, list);
	}

	@Override
	public void deleteByID(int sid) {
		sql = "delete from tblservices where serid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(sid);
		dbManage.executeSql(sql, list);
	}

	@Override
	public void updateServices(Services services) {
		sql = "update tblservices set sername=?,sermoney=?,sertype=? where serid=?";
		List<Object> list = new ArrayList<Object>();
		list.add(services.getSername());
		list.add(services.getSermoney());
		list.add(services.getSertype());
		list.add(services.getSerid());
		dbManage.executeSql(sql, list);
	}

	@Override
	public List<Services> selectAll() {
		sql = "select * from tblservices";
		rs = dbManage.executeQuery(sql, null);
		List<Services> list = new ArrayList<Services>();
		try {
				while(rs.next()){
					Services s = new Services();
					s.setSerid(rs.getInt("serid"));
					s.setSername(rs.getString("sername"));
					s.setSermoney(rs.getInt("sermoney"));
					s.setSertype(rs.getString("sertype"));
					list.add(s);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return list;
	}

	@Override
	public Services selectByIDReturnServices(int sid) {
		sql = "select * from tblservices where serid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(sid);
		rs =dbManage.executeQuery(sql, list);
		Services s = null;
		try {
				while(rs.next()){
					s = new Services();
					s.setSerid(rs.getInt("serid"));
					s.setSername(rs.getString("sername"));
					s.setSermoney(rs.getInt("sermoney"));
					s.setSertype(rs.getString("sertype"));
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
	public List<Services> selectByServicesType(String stype) {
		sql = "select * from tblservices where sertype = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(stype);
		rs =dbManage.executeQuery(sql, list);
		List<Services> listser = new ArrayList<Services>();
		try {
				while(rs.next()){
					Services s = new Services();
					s.setSerid(rs.getInt("serid"));
					s.setSername(rs.getString("sername"));
					s.setSermoney(rs.getInt("sermoney"));
					s.setSertype(rs.getString("sertype"));
					listser.add(s);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return listser;
	}

	@Override
	public List<Services> selectByName(String sname) {
		sql = "select * from tblservices where sername like ?";
		List<Object> list = new ArrayList<Object>();
		list.add("%" + sname + "%");
		rs = dbManage.executeQuery(sql, list);
		List <Services> listmember = new ArrayList<Services>();
		try {
			while(rs.next()){
				Services s = new Services();
				s.setSerid(rs.getInt("serid"));
				s.setSername(rs.getString("sername"));
				s.setSermoney(rs.getInt("sermoney"));
				s.setSertype(rs.getString("sertype"));
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
	public double selectByNameReturnPrice(String sname) {
		sql = "select * from tblservices where sername like ? and sertype='附加项目'";
		List<Object> list = new ArrayList<Object>();
		list.add("%" + sname + "%");
		rs = dbManage.executeQuery(sql, list);
		double serprice = 0;
		try {
			while(rs.next()){
				serprice = rs.getDouble("sermoney");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return serprice;
	}

	@Override
	public double selectByNamePrice(String sname) {
		sql = "select * from tblservices where sername like ? and sertype='常规项目'";
		List<Object> list = new ArrayList<Object>();
		list.add("%" + sname + "%");
		rs = dbManage.executeQuery(sql, list);
		double serprice = 0;
		try {
			while(rs.next()){
				serprice = rs.getDouble("sermoney");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return serprice;
	}

	@Override
	public int selectByNameNo(String sname) {
		sql = "select serid from tblservices where sername like ? and sertype='常规项目'";
		List<Object> list = new ArrayList<Object>();
		list.add("%" + sname + "%");
		rs = dbManage.executeQuery(sql, list);
		int sernum = 0;
		try {
			while(rs.next()){
				sernum = rs.getInt("serid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return sernum;
	}

	@Override
	public int selectByNameReturnID(String sname) {
		sql = "select * from tblservices where sername like ?";
		List<Object> list = new ArrayList<Object>();
		list.add("%" + sname + "%");
		rs = dbManage.executeQuery(sql, list);
		int conserid = 0;
		try {
			while(rs.next()){
				conserid = rs.getInt("serid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			closeSelect();
		}
		return conserid;
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
