package com.yjm.service;

import java.util.List;

import com.yjm.dao.ServicesDAO;
import com.yjm.dao.impl.ServicesDAOImpl;
import com.yjm.model.Services;

/**
 * 服务项目数据业务逻辑层
 * @author lxm
 * @date 2014-3-8 18:27:04
 */
public class ServicesService {
	
	private ServicesDAO dao = new ServicesDAOImpl();
	
	private static ServicesService ss = null;
	private ServicesService(){}
	
	public static synchronized ServicesService getInstace(){
		if(ss == null){
			ss = new ServicesService();
		}
		return ss;
	}
	
	public void addService(Services s){
		dao.addService(s);
	}
	
	//按照ID删除服务对象
	public void deleteByID(int serid){
		dao.deleteByID(serid);
	}

	//更改
	public void updateServices(Services s){
		dao.updateServices(s);
	}
	//查询全部
	public List<Services> selectAll(){
		return dao.selectAll();
	}
	
	//按照ID查询全部返回SERVICE对象
	public Services selectByIDReturnServices(int serid){
		return dao.selectByIDReturnServices(serid);
	}
	
	//按照ID查询全部返回SERVICE对象
	public List<Services> selectByServicesType(String sertype){
		return dao.selectByServicesType(sertype);
	}
	
	//按照姓名号码查询
	public List<Services> selectByName(String sername){
		return dao.selectByName(sername);
	}
	
	//查询附加项目的价格
	public double selectByNameReturnPrice(String sername){
		return dao.selectByNameReturnPrice(sername);
	}
	
	//查询常规项目的价格
	public double selectByNamePrice(String sername){
		return dao.selectByNamePrice(sername);
	}
	
	//查询常规项目的价格
	public int selectByNameNo(String sername){
		return dao.selectByNameNo(sername);
	}
	
	//按照服务名字查找ID
	public int selectByNameReturnID(String sname){
		return dao.selectByNameReturnID(sname);
	}
}






