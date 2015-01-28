package com.yjm.service;

import java.util.List;

import com.yjm.dao.CardDAO;
import com.yjm.dao.impl.CardDAOImpl;
import com.yjm.model.Card;
/**
 * 会员卡业务逻辑层
 * @author lxm
 * @date 2014-3-12 23:14:04
 */
public class CardService {

	private CardDAO dao = new CardDAOImpl();

	private static CardService ss = null;

	private CardService() {
	}

	public static synchronized CardService getInstance() {
		if (ss == null) {
			ss = new CardService();
		}
		return ss;
	}
	
	public boolean addCard(Card card){
		int result = dao.addCard(card);
		if(result>0){
			return true;
		}
		return false;
	}
	
	public boolean updateCard(Card card){
		int result = dao.updateCard(card);
		if(result>0){
			return true;
		}
		return false;
	}
	
	public boolean updateCardBalance(int cardid,int cardBalance){
		int result = dao.updateCardBalance(cardid, cardBalance);
		if(result>0){
			return true;
		}
		return false;
	}
	
	public boolean earnCardBalance(int cardid,int cardBalance){
		int result = dao.earnCardBalance(cardid, cardBalance);
		if(result>0){
			return true;
		}
		return false;
	}
	
	public boolean deleteCard(int cardid){
		int result = dao.deleteCard(cardid);
		if(result>0){
			return true;
		}
		return false;
	}
	
	public Card queryByOwner(String owner){
		return dao.queryByOwner(owner);
	}
	
	public Card queryByNum(String Num){
		return dao.queryByNum(Num);
	}
	
	public List<Card> queryAll(){
		return dao.queryAll();
	}
}
