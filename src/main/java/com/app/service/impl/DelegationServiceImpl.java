package com.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.dao.DelegationMapper;
import com.app.pojo.Delegation;
import com.app.service.IDelegationService;

@Service("delegationService")
public class DelegationServiceImpl implements IDelegationService {

	@Resource
	private DelegationMapper delegationDao;
	@Override
	public Delegation getDelegationById(Integer id) {
		return delegationDao.selectByPrimaryKey(id);
	}

}
