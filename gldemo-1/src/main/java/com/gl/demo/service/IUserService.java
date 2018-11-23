/**
 * 
 */
package com.gl.demo.service;

import java.util.List;
import java.util.Optional;

import com.gl.demo.model.User;

/**
 * The Interface IUserService.
 *
 * @author vikas.kumar3
 */
public interface IUserService {
	Optional<User> findOne(String id);

    List<User> findAll();
    
    User syncCall(String id);

    List<User> findByName(String name);

    List<User> findByEmail(String email);

    void create(User emp);

    void update(User emp);

    void delete(User emp);
}
