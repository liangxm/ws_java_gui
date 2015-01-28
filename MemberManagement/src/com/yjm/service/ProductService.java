package com.yjm.service;

import java.util.List;

import com.yjm.dao.ProductDAO;
import com.yjm.dao.impl.ProductDAOImpl;
import com.yjm.model.Product;
/**
 * ������ҵ���߼�ģ��
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

	// ����IDɾ����Ա
	public void deleteByID(String prid) {
		dao.deleteByID(prid);
	}

	// ���ձ��ID����ȫ����Ϣ
	public void updateProduct(Product product) {
		dao.updateProduct(product);
	}

	// ��ѯȫ��
	public List<Product> selectAll() {
		return dao.selectAll();
	}

	// ���ձ��mid�����ѯ
	public Product selectBymid(String prid) {
		return dao.selectBymid(prid);
	}
	
	// ���ձ��mid�����ѯ
	public boolean isProductExist(String prid) {
		return dao.isProductExist(prid);
	}

	// �����ֻ������ѯ
	public List<Product> selectByName(String pname) {
		return dao.selectByName(pname);
	}
}
