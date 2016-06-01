package com.app.dao;

import com.app.pojo.Delegation;

public interface DelegationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Delegation record);

    int insertSelective(Delegation record);

    Delegation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Delegation record);

    int updateByPrimaryKey(Delegation record);

	int checkUsername(String userName);
}