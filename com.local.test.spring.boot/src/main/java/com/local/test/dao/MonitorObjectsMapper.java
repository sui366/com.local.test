package com.local.test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.local.test.pojo.MonitorObjects;

@Mapper
public interface MonitorObjectsMapper {

	@Results({ @Result(property = "productType", column = "product_type"), @Result(property = "procType", column = "proc_type") })
	@Select("SELECT * FROM monitor_objects WHERE id = #{id}")
	MonitorObjects findById(@Param("id") Long id);
}
