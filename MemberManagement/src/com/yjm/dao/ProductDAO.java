package com.yjm.dao;

import java.util.List;

import com.yjm.model.Product;
/**
 * 商品数据持久层接口
 * @author lxm
 * @date 2014-3-8 11:47:50
 */
public interface ProductDAO {

	public void addProduct(Product product);
	public void deleteByID(String pid);
	public void updateProduct(Product product);
	public List<Product> selectAll();
	public Product selectBymid(String mid);
	public boolean isProductExist(String mid);
	public List<Product> selectByName(String pname);
	public void closeSelect();
	
}
