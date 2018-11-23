/*
 * PEARSON PROPRIETARY AND CONFIDENTIAL INFORMATION SUBJECT TO NDA 
 * Copyright (c) 2017 Pearson Education, Inc.
 * All Rights Reserved. 
 * 
 * NOTICE: All information contained herein is, and remains the property of 
 * Pearson Education, Inc. The intellectual and technical concepts contained 
 * herein are proprietary to Pearson Education, Inc. and may be covered by U.S. 
 * and Foreign Patents, patent applications, and are protected by trade secret 
 * or copyright law. Dissemination of this information, reproduction of this  
 * material, and copying or distribution of this software is strictly forbidden   
 * unless prior written permission is obtained from Pearson Education, Inc.
 */
package com.gl.demo.handler;
import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.demo.model.Token;
import com.gl.demo.service.TokenService;


/**
 * OauthTokenHandler will handle oauth access token request.
 * 
 * @author Vikas Kumar
 * 
 */
@RestController
public class OauthTokenHandler {
	
	/** The end point. */
	@Autowired
	private TokenEndpoint endPoint;
	
    @PostMapping(value = {"/v1/oauth/token"})
	public ResponseEntity<Token> oauthAccessToken(Principal principal,
			@RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
			Token token = TokenService.createToken(endPoint.postAccessToken(principal, parameters));
			return new ResponseEntity<Token>(token, HttpStatus.CREATED);
	}
}
