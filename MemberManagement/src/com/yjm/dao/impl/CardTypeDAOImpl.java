package com.yjm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yjm.dao.CardTypeDAO;
import com.yjm.jdbc.DBManage;
import com.yjm.model.CardType;

/**
 * 卡类型数据库持久层实现
 * @author lxm
 * @date 2014-3-13 23:30:28
 */
public class CardTypeDAOImpl implements CardTypeDAO {

	private ResultSet rs;
	private String strSql;
	private List<Object> list;
	private DBManage dbManage = DBManage.getInstance();

	@Override
	public int addCardType(CardType cardType) {
		strSql = "insert into tblcardtype(typename,typediscount,typestartamt) values(?,?,?)";
		list = new ArrayList<Object>();
		list.add(cardType.getTypename());
		list.add(cardType.getTypediscount());
		list.add(cardType.getTypestartamt());
		return dbManage.executeSql(strSql, list);
	}

	@Override
	public int updateCardType(CardType cardType) {
		strSql = "update tblcardtype set typename=?,typediscount=?,typestartamt=? where typeid = ?";
		list = new ArrayList<Object>();
		list.add(cardType.getTypename());
		list.add(cardType.getTypediscount());
		list.add(cardType.getTypestartamt());
		list.add(cardType.getTypeid());
		return dbManage.executeSql(strSql, list);
	}

	@Override
	public int deleteCardType(int cardtypeid) {
		strSql = "delete from tblcardtype where typeid = " + cardtypeid;
		return dbManage.executeSql(strSql);
	}

	@Override
	public List<CardType> queryAll() {
		strSql = "select * from tblcardtype";
		List<CardType> cardTypes = new ArrayList<CardType>();
		rs = dbManage.executeQuery(strSql);
		try {
			while (rs.next()) {
				CardType cardType = new CardType();
				cardType.setTypeid(rs.getInt("typeid"));
				cardType.setTypename(rs.getString("typename"));
				cardType.setTypediscount(rs.getInt("typediscount"));
				cardType.setTypestartamt(rs.getInt("typestartamt"));
				cardTypes.add(cardType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeSelect();
		}
		return cardTypes;
	}
	
	@Override
	public CardType queryByName(String typename) {
		strSql = "select * from tblcardtype where typename = '"+typename+"'";
		rs = dbManage.executeQuery(strSql);
		CardType cardType = new CardType();
		try {
			while (rs.next()) {
				cardType.setTypeid(rs.getInt("typeid"));
				cardType.setTypename(rs.getString("typename"));
				cardType.setTypediscount(rs.getInt("typediscount"));
				cardType.setTypestartamt(rs.getInt("typestartamt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeSelect();
		}
		return cardType;
	}
	
	@Override
	public CardType queryByID(int typeid) {
		strSql = "select * from tblcardtype where typeid = " + typeid;
		rs = dbManage.executeQuery(strSql);
		CardType cardType = new CardType();
		try {
			while (rs.next()) {
				cardType.setTypeid(rs.getInt("typeid"));
				cardType.setTypename(rs.getString("typename"));
				cardType.setTypediscount(rs.getInt("typediscount"));
				cardType.setTypestartamt(rs.getInt("typestartamt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeSelect();
		}
		return cardType;
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
