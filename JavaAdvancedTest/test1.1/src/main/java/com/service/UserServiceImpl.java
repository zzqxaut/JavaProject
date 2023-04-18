package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.PersonDao;
import com.po.Person;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PersonDao personDao;
	public Person selectPersonById1(Integer uid) {
		// TODO Auto-generated method stub
		return personDao.selectPersonById1(uid);
	}

}
