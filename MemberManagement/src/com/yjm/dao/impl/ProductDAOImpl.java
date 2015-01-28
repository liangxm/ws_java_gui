package com.yjm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yjm.dao.ProductDAO;
import com.yjm.jdbc.DBManage;
import com.yjm.model.Product;
/**
 * 商品数据持久层实现
 * @author lxm
 * @date 2014-3-8 11:50:34
 */
public class ProductDAOImpl implements ProductDAO {
	
	private ResultSet rs;
	private String sql;
	private DBManage dbManage = DBManage.getInstance();

	@Override
	public void addProduct(Product product) {
		sql = "insert into tblproduct(prid,pname,pcount,pperprice,pfrom,pby,penterdate,pindate,pexpiry) values(?,?,?,?,?,?,?,?,?)";
		List<Object> list = new ArrayList<Object>();
		list.add(product.getPrid());
		list.add(product.getPname());
		list.add(product.getPcount());
		list.add(product.getPperprice());
		list.add(product.getPfrom());
		list.add(product.getPby());
		list.add(product.getPenterdate());
		list.add(product.getPindate());
		list.add(product.getPexpiry());
		dbManage.executeSql(sql, list);
	}

	@Override
	public void deleteByID(String pid) {
		sql = "delete from tblproduct where prid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(pid);
		dbManage.executeSql(sql, list);
	}

	@Override
	public void updateProduct(Product product) {
		sql = "update tblproduct set pname = ?, pcount = ?,pperprice = ?,pfrom = ?,pby = ?,penterdate = ?,pexpiry = ? where prid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(product.getPname());
		list.add(product.getPcount());
		list.add(product.getPperprice());
		list.add(product.getPfrom());
		list.add(product.getPby());
		list.add(product.getPenterdate());
		list.add(product.getPexpiry());
		list.add(product.getPrid());
		dbManage.executeSql(sql, list);
	}

	@Override
	public List<Product> selectAll() {
		sql = "select * from tblproduct";
		rs = dbManage.executeQuery(sql, null);
		List<Product> list = new ArrayList<Product>();
		try {
			while (rs.next()) {
				Product m = new Product();
				m.setPrid(rs.getString("prid"));
				m.setPname(rs.getString("pname"));
				m.setPcount(rs.getInt("pcount"));
				m.setPperprice(rs.getDouble("pperprice"));
				m.setPfrom(rs.getString("pfrom"));
				m.setPby(rs.getString("pby"));
				m.setPindate(rs.getTimestamp("pindate"));
				m.setPenterdate(rs.getDate("penterdate"));
				m.setPexpiry(rs.getDate("pexpiry"));
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
	public Product selectBymid(String mid) {
		String strSql = "select * from tblproduct where prid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(mid);
		Product product = null;
		rs = dbManage.executeQuery(strSql, list);
		try {
			while (rs.next()) {
				product = new Product(rs.getInt("pid"), rs.getString("prid"),
						rs.getString("pname"), rs.getInt("pcount"),
						rs.getDouble("pperprice"),
						rs.getDate("penterdate"),
						rs.getTimestamp("pindate"), rs.getString("pfrom"),
						rs.getString("pby"), rs.getDate("pexpiry"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return product;
	}

	@Override
	public boolean isProductExist(String mid) {
		String strSql = "select * from tblproduct where prid = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(mid);
		rs = dbManage.executeQuery(strSql, list);
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
	public List<Product> selectByName(String pname) {
		sql = "select * from tblproduct where pname like ?";
		List<Object> list = new ArrayList<Object>();
		list.add("%" + pname + "%");
		rs = dbManage.executeQuery(sql, list);
		List<Product> listProduct = new ArrayList<Product>();
		try {
			while (rs.next()) {
				Product product = new Product(rs.getInt("pid"), rs.getString("prid"),
						rs.getString("pname"), rs.getInt("pcount"),
						rs.getDouble("pperprice"),
						rs.getDate("penterdate"),
						rs.getTimestamp("pindate"), rs.getString("pfrom"),
						rs.getString("pby"), rs.getDate("pexpiry"));
				listProduct.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return listProduct;
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
