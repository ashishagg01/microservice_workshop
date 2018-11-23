/*
 * PEARSON PROPRIETARY AND CONFIDENTIAL INFORMATION SUBJECT TO NDA
 * Copyright (c) 2017 Pearson Education, Inc.
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of
 * Pearson Education, Inc. The intellectual and technical concepts contained
 * herein are proprietary to Pearson Education, Inc. and may be covered by U.S.
 * and Foreign Patents, patent applications, and are protected by trade secret
 * or copyright law. Dissemination of this information, reproduction of this
 * material, and copying or distribution of this software is strictly forbidden
 * unless prior written permission is obtained from Pearson Education, Inc.
 */
package com.gl.demo.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.gl.demo.model.User;

/**
 * RestUtils is the main entry point to the fluent API used to build and
 * execute client requests in order to consume responses returned.
 */
public final class RestUtils {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(RestUtils.class);

    /** The shared RestTemplateBuilder. */
    private static RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
    
    /** The shared RestTemplate. */
    private static RestTemplate restTemplate = restTemplateBuilder.build();
 
    /**
     * Instantiates a new rest client.
     */
    private RestUtils() {
        super();
    }
    
    
    /**
     * Gets the.
     *
     * @param <T> the generic type
     * @param url the url
     * @param mediaType the media type
     * @param claazz the claazz
     * @return the t
     */
    public static <T> T get(final String url, final MediaType mediaType,
            Class<T> claazz)
    {
        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Arrays.asList(mediaType));

        final HttpEntity<Object> requestEntity = new HttpEntity<Object>(
                requestHeaders);
        return restTemplateExchange(url, HttpMethod.GET, requestEntity, claazz);
    }

	public static <T> ResponseEntity<List<User>> get(String url, MediaType applicationJson,
			ParameterizedTypeReference<List<User>> parameterizedTypeReference) {
		final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Arrays.asList(applicationJson));

        final HttpEntity<Object> requestEntity = new HttpEntity<Object>(
                requestHeaders);
        try {
        	return restTemplate.exchange(url, HttpMethod.GET, requestEntity, parameterizedTypeReference); 		
        } catch (RestClientException ex) {
        	LOGGER.error("RestUtils Error while {} "+HttpMethod.GET+" to {} \""+url+"\". Exception occurred : {} ", ex);
        	throw ex;
        }
	}
    
    /**
     * Gets the.
     *
     * @param <T> the generic type
     * @param url the url
     * @param mediaType the media type
     * @param headers the headers
     * @param claazz the claazz
     * @return the t
     */
    public static <T> T get(final String url, final MediaType mediaType,
            final Map<String, String> headers, Class<T> claazz)
    {
        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAll(headers);
        requestHeaders.setAccept(Arrays.asList(mediaType));

        final HttpEntity<Object> requestEntity = new HttpEntity<Object>(
                requestHeaders);
        return restTemplateExchange(url, HttpMethod.GET, requestEntity, claazz);
    }

    
    /**
     * Post.
     *
     * @param <T> the generic type
     * @param url the url
     * @param mediaType the media type
     * @param postParameter the post parameter
     * @param clazz the clazz
     * @return the t
     */
    public static <T> T post(final String url, final MediaType mediaType,
            final Object postParameter, final Class<T> clazz)
    {
        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Arrays.asList(mediaType));
        requestHeaders.setContentType(mediaType);

        final HttpEntity<Object> requestEntity = new HttpEntity<Object>(postParameter,
                requestHeaders);
        return restTemplateExchange(url, HttpMethod.POST, requestEntity, clazz);
    }

    
    /**
     * Post.
     *
     * @param <T> the generic type
     * @param url the url
     * @param mediaType the media type
     * @param postParameter the post parameter
     * @param clazz the clazz
     * @param headers the headers
     * @return the t
     */
    public static <T> T post(final String url, final MediaType mediaType,
            final Object postParameter, final Class<T> clazz,
            final Map<String, String> headers)
    {
        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAll(headers);
        requestHeaders.setAccept(Arrays.asList(mediaType));
        requestHeaders.setContentType(mediaType);

        final HttpEntity<Object> requestEntity = new HttpEntity<Object>(postParameter,
                requestHeaders);
        return restTemplateExchange(url, HttpMethod.POST, requestEntity, clazz);
    }

    /**
     * Rest template exchange.
     *
     * @param <T> the generic type
     * @param url the url
     * @param method the method
     * @param requestEntity the request entity
     * @param clazz the clazz
     * @return the t
     */
    public static <T> T restTemplateExchange(
    		String url, 
    		HttpMethod method, 
    		HttpEntity<Object> requestEntity,
    		Class<T> clazz
    		)
    {
    	try {
        	return restTemplate.exchange(url, method, requestEntity, clazz).getBody(); 		
        } catch (RestClientException ex) {
        	LOGGER.error("RestUtils Error while {} "+method+" to {} \""+url+"\". Exception occurred : {} ", ex);
        	throw ex;
        }    	
    }
    
    /**
     * Sets the rest template.
     *
     * @param restTemplate the new rest template
     */
    public static void setRestTemplate(RestTemplate restTemplate) {
    	RestUtils.restTemplate = restTemplate;
    }
    
    /**
     * Gets the rest template.
     *
     * @return the rest template
     */
    public static RestTemplate getRestTemplate() {
    	return restTemplate;
    }
    
}
