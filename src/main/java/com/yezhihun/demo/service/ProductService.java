package com.yezhihun.demo.service;

import com.yezhihun.demo.entity.Product;
import com.yezhihun.demo.entity.ProductImage;

import java.util.List;

/**
 * Created by tianye on 2018/5/3.
 */
public interface ProductService extends BaseService<Product> {
	/**
	 * 根据商品ID查询商品信息
	 * 
	 * @param productId
	 */
	public Product getByProdcutId(Long productId);

	public void publish(Product p,List<ProductImage> imageList);
}
