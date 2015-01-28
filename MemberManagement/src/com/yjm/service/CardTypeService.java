package com.yjm.service;

import java.util.List;

import com.yjm.dao.CardTypeDAO;
import com.yjm.dao.impl.CardTypeDAOImpl;
import com.yjm.model.CardType;

/**
 * 卡类型业务逻辑层
 * @author lxm
 * @date 2014-3-12 23:01:15
 */
public class CardTypeService {
	private CardTypeDAO dao = new CardTypeDAOImpl();
	
	private static CardTypeService ss = null;
	
	private CardTypeService(){}
	
	public static synchronized CardTypeService getInstance(){
		if(ss == null){
			ss = new CardTypeService();
		}
		return ss;
	}
	
	public boolean addCardType(CardType cardType){
		int result = dao.addCardType(cardType);
		if(result>0){
			return true;
		}
		return false;
	}
	
	public boolean updateCardType(CardType cardType){
		int result = dao.updateCardType(cardType);
		if(result>0){
			return true;
		}
		return false;
	}
	
	public boolean deleteCardType(int cardtypeid){
		int result = dao.deleteCardType(cardtypeid);
		if(result>0){
			return true;
		}
		return false;
	}
	
	public CardType queryByName(String typename){
		return dao.queryByName(typename);
	}
	
	public CardType queryByID(int typeid){
		return dao.queryByID(typeid);
	}
	
	public List<CardType> queryAll(){
		return dao.queryAll();
	}
}
