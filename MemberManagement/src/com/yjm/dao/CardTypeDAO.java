package com.yjm.dao;

import java.util.List;

import com.yjm.model.CardType;

/**
 * ��Ա���������ݳ־ò�ӿ�
 * @author lxm
 * @date 2014-3-12 21:58:34
 */
public interface CardTypeDAO {

	public int addCardType(CardType cardType);
	public int updateCardType(CardType cardType);
	public int deleteCardType(int cardtypeid);
	public CardType queryByName(String typename);
	public CardType queryByID(int typeid);
	public List<CardType> queryAll();
	public void closeSelect();
}
