package com.yjm.dao;

import java.util.List;

import com.yjm.model.Services;
/**
 * 服务项目数据持久层接口
 * @author lxm
 * @date 2014-3-8 11:47:18
 */
public interface ServicesDAO {

	public void addService(Services services);
	public void deleteByID(int sid);
	public void updateServices(Services services);
	public List<Services> selectAll();
	public Services selectByIDReturnServices(int sid);
	public List<Services> selectByServicesType(String stype);
	public List<Services> selectByName(String sname);
	public double selectByNameReturnPrice(String sname);
	public double selectByNamePrice(String sname);
	public int selectByNameNo(String sname);
	public int selectByNameReturnID(String sname);
	public void closeSelect();
	
}
