package com.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.po.Person;

@Repository
@Mapper
public interface PersonDao {
	public Person selectPersonById1(Integer id);
}
