package com.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.po.IdCard;

@Repository
@Mapper
public interface IdCardDao {

	public IdCard selectCodeById(Integer id);
}
