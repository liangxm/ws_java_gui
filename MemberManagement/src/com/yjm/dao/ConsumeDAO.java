package com.yjm.dao;

import java.util.List;

import com.yjm.model.Consume;

/**
 * 收银数据库持久层接口
 * @author lxm
 * @date 2014-3-8 11:32:04
 */
public interface ConsumeDAO {

	public void addConsume(Consume consume);
	public void updateConsume(Consume consume);
	public void updateConsumeByConID(int conID);
	public void updateConsumePhoneByConID(String phone,int conID);
	public List<Consume> selectAll();
	public List<Consume> selectByConID(int conID);
	public Consume selectByConIDReturnConsume(int conID);
	public List<Consume> selectNoMember();
	public List<Consume> selectByPhone(String phone);
	public List<Consume> selectByConEmID(int emID);
	public List<Consume> selectByConEmName(String conName);
	public int selectByinDate(String inDate);
	public int selectByMonth(int month);
	public int selectByYear(String year);
	public int selectByTime(String start,String end);
	public int selectCountByServicesNo(int sid);
	public void deleteByID(int conID);
	public void closeSelect();

}
