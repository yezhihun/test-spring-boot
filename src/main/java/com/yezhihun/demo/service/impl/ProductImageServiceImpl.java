package com.yezhihun.demo.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yezhihun.demo.dao.ProductImageDao;
import com.yezhihun.demo.entity.ProductImage;
import com.yezhihun.demo.service.ProductImageService;

@Service
public class ProductImageServiceImpl extends AbstractBaseServiceImpl<ProductImage> implements ProductImageService {
	@Autowired
	private ProductImageDao productImageDao;

	@Override
	@PostConstruct
	public void init() {
		this.baseDao = productImageDao;
	}

	@Override
	public List<ProductImage> getByProductId(Long productId) {
		return productImageDao.getByProductId(productId);
	}
}
