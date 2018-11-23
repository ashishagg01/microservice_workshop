package com.gl.demo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gl.demo.service.TokenService;

import java.util.Map;
import java.util.UUID;

/**
 * The Class TestHandler.
 * @author vikas.kumar3
 *
 */
@RestController
@RequestMapping("/me")
public class TestHandler {
	
	@Autowired
	private TokenService token;

    @RequestMapping(method = RequestMethod.GET)
    //@PreAuthorize("hasAuthority('WRITE')")
    public String readFoo() {
    	Map<String, Object> map = token.fetchClaims();
        return "read " + UUID.randomUUID().toString();
    }

    //@PreAuthorize("hasAuthority('WRITE')")
    @RequestMapping(method = RequestMethod.POST)
    public String writeFoo() {
        return "write " + UUID.randomUUID().toString();
    }
}
