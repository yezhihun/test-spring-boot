package com.yezhihun.demo.dao;

import com.yezhihun.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by tianye on 2018/5/3.
 */
@Repository
public interface UserDao extends BaseDao<User>{

    @Query("select t from User t where t.id = :id")
    User findById(Integer id);
}
