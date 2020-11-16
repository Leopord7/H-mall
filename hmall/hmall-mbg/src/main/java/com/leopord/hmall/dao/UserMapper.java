package com.leopord.hmall.dao;

import com.leopord.hmall.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUsername(String name);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}