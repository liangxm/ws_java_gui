package com.yjm.service;

import java.util.List;

import com.yjm.dao.ProductDAO;
import com.yjm.dao.impl.ProductDAOImpl;
import com.yjm.model.Product;
/**
 * 库存管理业务逻辑模型
 * @author lxm
 * @date 2014-3-8 18:13:20
 */
public class ProductService {
	
	private ProductDAO dao = new ProductDAOImpl();
	
	private static ProductService ss = null;
	private ProductService(){}
	
	public static synchronized ProductService getInstace(){
		if(ss == null){
			ss = new ProductService();
		}
		return ss;
	}

	public void addProduct(Product product) {
		dao.addProduct(product);
	}

	// 按照ID删除会员
	public void deleteByID(String prid) {
		dao.deleteByID(prid);
	}

	// 按照编号ID更改全部信息
	public void updateProduct(Product product) {
		dao.updateProduct(product);
	}

	// 查询全部
	public List<Product> selectAll() {
		return dao.selectAll();
	}

	// 按照编号mid号码查询
	public Product selectBymid(String prid) {
		return dao.selectBymid(prid);
	}
	
	// 按照编号mid号码查询
	public boolean isProductExist(String prid) {
		return dao.isProductExist(prid);
	}

	// 按照手机号码查询
	public List<Product> selectByName(String pname) {
		return dao.selectByName(pname);
	}
}
