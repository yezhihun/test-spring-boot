package com.yezhihun.demo.service.impl;

import com.yezhihun.demo.dao.ProductDao;
import com.yezhihun.demo.dao.ProductImageDao;
import com.yezhihun.demo.entity.Product;
import com.yezhihun.demo.entity.ProductImage;
import com.yezhihun.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl extends AbstractBaseServiceImpl<Product> implements ProductService {
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductImageDao productImageDao;

	@Override
	@PostConstruct
	public void init() {
		this.baseDao = productDao;
	}

	@Override
	public Product getByProdcutId(Long productId) {
		Product p = productDao.getByProductId(productId);
		return null == p ? new Product() : p;
	}

	@Override
	@Transactional()
	public void publish(Product p, List<ProductImage> imageList) {
		productDao.save(p);
		productImageDao.batchSave(imageList);

		throw new RuntimeException("发生异常了..");

	}
	
	

}
