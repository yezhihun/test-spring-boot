package com.yezhihun.demo.service;

import java.util.List;

import com.yezhihun.demo.entity.ProductImage;

/**
 * Created by tianye on 2018/5/3.
 */
public interface ProductImageService extends BaseService<ProductImage> {
	/**
	 * 根据商品ID查询商品图片
	 * 
	 * @param productId
	 * @return
	 */
	public List<ProductImage> getByProductId(Long productId);
}
