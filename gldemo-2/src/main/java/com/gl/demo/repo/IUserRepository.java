package com.gl.demo.repo;

import java.util.List;

import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;

import com.gl.demo.model.User;


@ViewIndexed(designDoc = "user")
public interface IUserRepository extends CrudRepository<User, String> {
	List<User> findByName(String name);
    List<User> findByEmail(String email);
}
