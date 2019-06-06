package com.yezhihun.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yezhihun.demo.entity.ProductImage;

/**
 * Created by tianye on 2018/5/3.
 */
@Repository
public interface ProductImageDao extends BaseDao<ProductImage> {

	@Query("select t from ProductImage t where t.productId = :productId")
	List<ProductImage> getByProductId(@Param("productId") Long productId);

}
