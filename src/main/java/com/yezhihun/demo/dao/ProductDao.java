package com.yezhihun.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yezhihun.demo.entity.Product;

/**
 * Created by tianye on 2018/5/3.
 */
@Repository
public interface ProductDao extends BaseDao<Product> {

	@Query("select t from Product t where t.productId = :productId")
	Product getByProductId(@Param("productId") Long productId);

}
