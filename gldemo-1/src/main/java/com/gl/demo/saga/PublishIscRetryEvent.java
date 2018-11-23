package com.gl.demo.saga;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.demo.repo.EventsRetryModelRepository;

@Service
public class PublishIscRetryEvent {

	private static final String UNIQUE_IDENTIFIER = "uniqueIdentifier";

	private static final String EVENTS_HYPEN = "Events-";

	private static final String EMPTY_STRING = "";

	@Autowired
	private EventsRetryModelRepository repo;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PublishIscRetryEvent.class);

	public PublishIscRetryEvent() {
		super();
	}

	public void publishIscRetryEvent(String eventType, String eventContext) {
		LOGGER.debug("Entering publishIscRetryEvent for eventType {} and eventContext {}", eventType, eventContext);

		String uniqueIdentifier = String.join(EMPTY_STRING, EVENTS_HYPEN, String.valueOf(UUID.randomUUID()));
		EventsRetryModel retryModel = new EventsRetryModel(uniqueIdentifier, eventType, eventContext);
		repo.save(retryModel);
		LOGGER.debug(
				"Entering publishIscRetryEvent after saving with uniqueId {} and  eventType {} and eventContext {}",
				uniqueIdentifier, eventType, eventContext);
		LOGGER.info("sending payload='{}' to topic='{}'", eventContext, eventType);
		try {
			kafkaTemplate.send(eventType, new ObjectMapper().writeValueAsString(retryModel));
		} catch (JsonProcessingException e) {
			LOGGER.error("Exception Occurred");
		}
	}
}
