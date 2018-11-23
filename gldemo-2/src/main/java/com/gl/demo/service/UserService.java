package com.gl.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl.demo.model.User;
import com.gl.demo.repo.IUserRepository;

@Component
public class UserService {
	
	@Autowired
	private IUserRepository repo;
	
	public Optional<User> findOne(String id) {
		return repo.findById(id);
	}

	public List<User> findAll() {
		List<User> emp = new ArrayList<User>();
        Iterator<User> it = repo.findAll().iterator();
        while (it.hasNext()) {
            emp.add(it.next());
        }
		return emp;
	}
}
