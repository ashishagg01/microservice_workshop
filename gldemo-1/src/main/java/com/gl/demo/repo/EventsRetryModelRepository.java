package com.gl.demo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gl.demo.saga.EventsRetryModel;

@Repository
public interface EventsRetryModelRepository extends CrudRepository<EventsRetryModel, String> {

	public List<EventsRetryModel> findBydocTypeAndAcknowledged(String docType, boolean isack);

}
