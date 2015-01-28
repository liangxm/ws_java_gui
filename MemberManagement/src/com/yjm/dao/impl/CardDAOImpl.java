package com.yjm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yjm.dao.CardDAO;
import com.yjm.jdbc.DBManage;
import com.yjm.model.Card;
/**
 * 卡数据库持久层实现 
 * @author lxm
 * @date 2014-3-10 21:18:10
 */
public class CardDAOImpl implements CardDAO {
	
	private ResultSet rs;
	private String strSql;
	private List<Object> list;
	private DBManage dbManage = DBManage.getInstance();

	@Override
	public int addCard(Card card) {
		strSql = "insert into tblcard(cardnumber,cardtype,cardowner,cardbalance,expirydate,activedate,active,block) values(?,?,?,?,?,now(),1,0)";
		list = new ArrayList<Object>();
		list.add(card.getCardnumber());
		list.add(card.getCardtype());
		list.add(card.getCardowner());
		list.add(card.getCardbalace());
		list.add(card.getExpirydate());
		return dbManage.executeSql(strSql, list);
	}

	@Override
	public int updateCard(Card card) {
		strSql = "update tblcard set cardtype=? where cardid = "+card.getCardid();
		list = new ArrayList<Object>();
		list.add(card.getCardnumber());
		list.add(card.getCardtype());
		list.add(card.getCardowner());
		return dbManage.executeSql(strSql, list);
	}
	
	@Override
	public int updateCardBalance(int cardid,int cardBalance) {
		strSql = "update tblcard set cardbalance = cardbalance - ? where cardid=?";
		list = new ArrayList<Object>();
		list.add(cardBalance);
		list.add(cardid);
		return dbManage.executeSql(strSql, list);
	}
	
	@Override
	public int earnCardBalance(int cardid, int cardBalance) {
		strSql = "update tblcard set cardbalance = cardbalance + ? where cardid=?";
		list = new ArrayList<Object>();
		list.add(cardBalance);
		list.add(cardid);
		return dbManage.executeSql(strSql, list);
	}

	@Override
	public int deleteCard(int cardid) {
		strSql = "delete from tblcard where cardid = "+cardid;
		return dbManage.executeSql(strSql);
	}
	
	@Override
	public Card queryByOwner(String owner) {
		strSql = "select * from tblcard where cardowner ='"+owner+"'";
		Card card = null;
		rs = dbManage.executeQuery(strSql);
		try{
			while(rs.next()){
				card = new Card();
				card.setCardid(rs.getInt("cardid"));
				card.setCardnumber(rs.getString("cardnumber"));
				card.setCardtype(rs.getString("cardtype"));
				card.setCardowner(rs.getString("cardowner"));
				card.setCardbalace(rs.getDouble("cardbalance"));
				card.setExpirydate(rs.getTimestamp("expirydate"));
				card.setActivedate(rs.getTimestamp("activedate"));
				card.setActive(rs.getBoolean("active"));
				card.setBlock(rs.getBoolean("block"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return card;
	}
	
	@Override
	public Card queryByNum(String Num) {
		strSql = "select * from tblcard where cardnumber ='"+Num+"'";
		Card card = new Card();
		rs = dbManage.executeQuery(strSql);
		try{
			while(rs.next()){
				card.setCardid(rs.getInt("cardid"));
				card.setCardnumber(rs.getString("cardnumber"));
				card.setCardtype(rs.getString("cardtype"));
				card.setCardowner(rs.getString("cardowner"));
				card.setCardbalace(rs.getDouble("cardbalance"));
				card.setExpirydate(rs.getTimestamp("expirydate"));
				card.setActivedate(rs.getTimestamp("activedate"));
				card.setActive(rs.getBoolean("active"));
				card.setBlock(rs.getBoolean("block"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return card;
	}

	@Override
	public List<Card> queryAll() {
		strSql = "select * from tblcard";
		List<Card> cards = new ArrayList<Card>();
		rs = dbManage.executeQuery(strSql);
		try{
			while(rs.next()){
				Card card = new Card();
				card.setCardid(rs.getInt("cardid"));
				card.setCardnumber(rs.getString("cardnumber"));
				card.setCardtype(rs.getString("cardtype"));
				card.setCardowner(rs.getString("cardowner"));
				card.setCardbalace(rs.getDouble("cardbalance"));
				card.setExpirydate(rs.getTimestamp("expirydate"));
				card.setActivedate(rs.getTimestamp("activedate"));
				card.setActive(rs.getBoolean("active"));
				card.setBlock(rs.getBoolean("block"));
				cards.add(card);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return cards;
	}

}
