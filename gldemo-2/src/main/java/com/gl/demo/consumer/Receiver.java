package com.gl.demo.consumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.demo.model.User;
import com.gl.demo.repo.IUserRepository;

@Component
public class Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
  
  @Autowired
  private IUserRepository repo;

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topics = "${kafka.topic}")
  public void receive(String payload) throws JsonParseException, JsonMappingException, IOException {
    LOGGER.info("received payload='{}'", payload);
    //latch.countDown();
    ObjectMapper mapper = new ObjectMapper();
    Map<String,Object> eventContextMap = mapper.readValue(payload, HashMap.class);
    User user = mapper.readValue(String.valueOf(eventContextMap.get("eventContext")), User.class);
    repo.save(user);
    
  }
}
