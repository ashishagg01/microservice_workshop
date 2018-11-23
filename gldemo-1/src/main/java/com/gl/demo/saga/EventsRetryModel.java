package com.gl.demo.saga;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class EventsRetryModel {

	/**
	 * 
	 */
	@Id
	private String id;

	private static final long serialVersionUID = 1L;

	private String eventType;

	private String eventContext;;

	private boolean acknowledged = Boolean.FALSE;

	private String eventPublishing = "PUBLISHING";

	private String docType = "RETRY_DOCUMENT";

	public EventsRetryModel(String uniqueId, String eventType, String eventContext) {
		this.id = uniqueId;
		this.eventType = eventType;
		this.eventContext = eventContext;
	}

	public EventsRetryModel() {
		super();
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType
	 *            the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the acknowledged
	 */
	public boolean isAcknowledged() {
		return acknowledged;
	}

	/**
	 * @param acknowledged
	 *            the acknowledged to set
	 */
	public void setAcknowledged(boolean acknowledged) {
		this.acknowledged = acknowledged;
	}

	/**
	 * @return the eventPublishing
	 */
	public String getEventPublishing() {
		return eventPublishing;
	}

	/**
	 * @param eventPublishing
	 *            the eventPublishing to set
	 */
	public void setEventPublishing(String eventPublishing) {
		this.eventPublishing = eventPublishing;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEventContext() {
		return eventContext;
	}

	public void setEventContext(String eventContext) {
		this.eventContext = eventContext;
	}
}
