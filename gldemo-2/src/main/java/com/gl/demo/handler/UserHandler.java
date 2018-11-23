/**
 * 
 */
package com.gl.demo.handler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.demo.model.User;
import com.gl.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Class UserHandler.
 *
 * @author vikas.kumar3
 */
@RestController
@Api(tags = "User Information")
@RequestMapping(path="/v2/user")
public class UserHandler {
	
	@Autowired
	private UserService service;
	
	@ApiOperation(value = "Fetching user by id")
	@GetMapping("/{id}")
	private ResponseEntity<Optional<User>> findUserById(@PathVariable(value = "id") String id){
		return ResponseEntity.ok(service.findOne(id));
	}
}
