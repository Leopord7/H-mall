package com.leopord.hmall.dao;

import com.leopord.hmall.entity.Sequence;

public interface SequenceMapper {
    int deleteByPrimaryKey(String name);

    int insert(Sequence record);

    int insertSelective(Sequence record);

    Sequence selectByPrimaryKey(String name);

    Sequence getSequenceByName(String name);

    int updateByPrimaryKeySelective(Sequence record);

    int updateByPrimaryKey(Sequence record);
}