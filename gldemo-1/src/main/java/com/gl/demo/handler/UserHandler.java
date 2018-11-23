/**
 * 
 */
package com.gl.demo.handler;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.demo.model.User;
import com.gl.demo.producer.Sender;
import com.gl.demo.saga.PublishIscRetryEvent;
import com.gl.demo.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Class UserHandler.
 *
 * @author vikas.kumar3
 */
@RestController
@Api(tags = "User Information")
@RequestMapping(path="/v1/user")
public class UserHandler {
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private PublishIscRetryEvent publishIscRetryEvent;
	
	@Autowired
	private Sender sender;
	
	@GetMapping
	public ResponseEntity<List<User>> allUser() {
		List<User> empList = service.findAll();
		if (empList.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(empList, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Fetching user by id")
	@GetMapping("/{id}")
	private ResponseEntity<Optional<User>> findUserById(@PathVariable(value = "id") String id){
		return ResponseEntity.ok(service.findOne(id));
	}
	@ApiOperation(value = "adding a new user")
	@PostMapping
	public ResponseEntity<User> createResource(@Valid @RequestBody User emp) {
		service.create(emp);
		try {
		    sender.send("dempTopic", new ObjectMapper().writeValueAsString(emp));
			//publishIscRetryEvent.publishIscRetryEvent("testTopic", new ObjectMapper().writeValueAsString(emp));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<User>(emp, HttpStatus.CREATED);
	}
	
	@GetMapping("/async/{id}")
	public ResponseEntity<User> findAsyncData(@PathVariable(value = "id") String id) {
		return new ResponseEntity<User>(service.syncCall(id), HttpStatus.OK);
	}
	
}
