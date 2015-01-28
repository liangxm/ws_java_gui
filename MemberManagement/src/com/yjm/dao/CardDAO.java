package com.yjm.dao;

import java.util.List;

import com.yjm.model.Card;

/**
 * 卡数据库持久层接口
 * @author lxm
 * @date 2014-3-10 21:17:41
 */
public interface CardDAO {

	public int addCard(Card card);
	public int updateCard(Card card);
	public int earnCardBalance(int cardid,int cardBalance);
	public int updateCardBalance(int cardid,int cardBalance);
	public int deleteCard(int cardid);
	public Card queryByOwner(String owner);
	public Card queryByNum(String Num);
	public List<Card> queryAll();
}
