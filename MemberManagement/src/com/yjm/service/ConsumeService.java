package com.yjm.service;

import java.util.List;

import com.yjm.dao.ConsumeDAO;
import com.yjm.dao.impl.ConsumeDAOImpl;
import com.yjm.model.Consume;

/**
 * 收银管理模块业务逻辑层
 * @author lxm
 * @date 2014-3-8 16:38:15
 */
public class ConsumeService {
	
	private ConsumeDAO dao = new ConsumeDAOImpl();
	
	private static ConsumeService ss = null;
	
	private ConsumeService(){}
	
	public static synchronized ConsumeService getInstace(){
		if(ss == null){
			ss = new ConsumeService();
		}
		return ss;
	}
	
	//增加
	public void addConsume(Consume c){
		dao.addConsume(c);
	}

	//更改
	public void updateConsume(Consume c){
		dao.updateConsume(c);
	}
	
	//根据消费ID更改
	public void updateConsumeByConID(int conid){
		dao.updateConsumeByConID(conid);
	}
	
	//按照conID更改电话号码
	public void updateConsumePhoneByConID(String conmphone ,int conid){
		dao.updateConsumePhoneByConID(conmphone, conid);
	}
	
	//查询全部
	public List<Consume> selectAll(){
		return dao.selectAll();
	}
	//按照消费ID查询
	
	public List<Consume> selectByConID(int conid){
		return dao.selectByConID(conid);
	}
	
	//按照消费ID查询返回对象
	public Consume selectByConIDReturnConsume(int conid){
		return dao.selectByConIDReturnConsume(conid);
	}
	
	//按照电话查询
	public List<Consume> selectNoMember(){
		return dao.selectNoMember();
	}
	
	//按照电话查询
	public List<Consume> selectByPhone(String conmphone){
		return dao.selectByPhone(conmphone);
	}
	
	//按照员工ID查询
	public List<Consume> selectByConEmID(int conemid){
		return dao.selectByConEmID(conemid);
	}
	
	//按照员工姓名查询
	public List<Consume> selectByConEmName(String emname){
		return dao.selectByConEmName(emname);
	}
	
	//按照日期查询(days)
	public int selectByInDate(String days){
		return dao.selectByinDate(days);
	}
	
	//按照日期查询(month)
	public int selectByMonth(int month){
		return dao.selectByMonth(month);
	}
	
	//按照日期查询(year)
	public int selectByYear(String year){
		return dao.selectByYear(year);
	}
	
	//按照日期查询(between)
	public int selectByTime(String start,String end){
		return dao.selectByTime(start, end);
	}
	
	public int selectCountByServicesNo(int sid) {
		return dao.selectCountByServicesNo(sid);
	}
	
	//按照ID删除消费记录
	public void deleteByID(int conemid){
		dao.deleteByID(conemid);		
	}
}
