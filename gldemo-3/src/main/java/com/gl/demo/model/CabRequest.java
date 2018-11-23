package com.gl.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;


public class CabRequest implements Serializable {
		

		/**
	 * 
	 */
	private static final long serialVersionUID = -5305229707047937759L;

		/** The id. */
	    private Long id;
	    
	    /** The name. */
	    private Long empId;
	    
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getEmpId() {
			return empId;
		}

		public void setEmpId(Long empId) {
			this.empId = empId;
		}

		public LocalDateTime getCreatedTime() {
			return createdTime;
		}

		public void setCreatedTime(LocalDateTime createdTime) {
			this.createdTime = createdTime;
		}

		/** The address. */
	    private LocalDateTime createdTime;

}
