package com.yezhihun.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yezhihun.demo.configuration.JedisProxy;
import com.yezhihun.demo.entity.Product;
import com.yezhihun.demo.entity.ProductImage;
import com.yezhihun.demo.service.ProductImageService;
import com.yezhihun.demo.service.ProductService;
import com.yezhihun.demo.vo.request.CreateProductVO;
import com.yezhihun.demo.vo.response.BaseJsonVo;

/**
 * Created by tianye on 2018/5/3.
 */
@RestController
@RequestMapping("/product/")
public class ProductController {

	@Autowired
	private JedisProxy jedisProxy;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductImageService productImageService;

	@ResponseBody
	@RequestMapping(value = "publish", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public BaseJsonVo publish(@RequestBody CreateProductVO vo) {
		Long userId = 98678968L;
		String jsonString = JSON.toJSONString(vo);
		System.out.println(jsonString);

		Long productId = vo.getProductId();
		Product p = new Product();
		p.setProductId(vo.getProductId());
		p.setProductName(vo.getProductName());
		p.setCreateTime(new Date());
		p.setStatus(0);
		p.setAuditStatus(0);
		p.setText(vo.getText());
		p.setLatitude(vo.getLatitude());
		p.setLongitude(vo.getLongitude());
		p.setPosition(vo.getPosition());
		p.setUserId(userId);

		String[] imageList = vo.getImageList();
		List<ProductImage> list = new ArrayList<>();
		if (null != imageList) {
			for (String url : imageList) {
				ProductImage i = new ProductImage();
				i.setImageUrl(url);
				i.setProductId(productId);
				list.add(i);
			}
		}
		productService.publish(p, list);
		return BaseJsonVo.success(p);
	}

	@ResponseBody
	@RequestMapping(value = "get", method = { RequestMethod.GET })
	public BaseJsonVo getProduct(@RequestParam(value = "productId") Long productId) {
		Product p = productService.getByProdcutId(productId);
		List<ProductImage> list = productImageService.getByProductId(productId);

		return BaseJsonVo.success(list);
	}
}
