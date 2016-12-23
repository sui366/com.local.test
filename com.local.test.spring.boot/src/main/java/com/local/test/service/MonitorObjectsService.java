package com.local.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.local.test.dao.MonitorObjectsMapper;
import com.local.test.pojo.MonitorObjects;

@Service
public class MonitorObjectsService {

	@Autowired
	MonitorObjectsMapper monitorObjectsMapper;
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public MonitorObjects findById(Long id){
		return monitorObjectsMapper.findById(id);
	}
}
