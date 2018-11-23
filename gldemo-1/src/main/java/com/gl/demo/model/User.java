/**
 * 
 */
package com.gl.demo.model;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Class User.
 * @author vikas.kumar3
 */
@Document
public class User implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -2702740197857402124L;

	/** The id. */
    @Id
    private String id;
    
    /** The name. */
    private String name;
    
    /** The email. */
    private String email;
    
    /** The address. */
    private String address;
    
    @JsonIgnore
    /** The created. */
    private DateTime created;
    
    /** The updated. */
    @JsonIgnore
    private DateTime updated;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(DateTime created) {
		this.created = created;
	}

	public DateTime getUpdated() {
		return updated;
	}

	public void setUpdated(DateTime updated) {
		this.updated = updated;
	}

}
