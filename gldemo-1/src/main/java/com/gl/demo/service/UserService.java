/**
 * 
 */
package com.gl.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.gl.demo.model.User;
import com.gl.demo.repo.IUserRepository;
import com.gl.demo.utils.RestUtils;

/**
 * The Class UserService.
 *
 * @author vikas.kumar3
 */
@Component
public class UserService implements IUserService {
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

	public List<User> findByName(String name) {
		return repo.findByName(name);
	}

	public List<User> findByEmail(String email) {
		return repo.findByEmail(email);
	}

	public void create(User emp) {
		emp.setId(UUID.randomUUID().toString());
		emp.setCreated(DateTime.now());
        repo.save(emp);
	}

	public void update(User emp) {
		emp.setUpdated(DateTime.now());
        repo.save(emp);
		
	}

	public void delete(User emp) {
		repo.delete(emp);
	}

	public User syncCall(String id) {
		String url = "http://localhost:9082/demo2/v2/user/"+id;
		return  RestUtils.get(url, MediaType.APPLICATION_JSON, User.class);
	}
}
