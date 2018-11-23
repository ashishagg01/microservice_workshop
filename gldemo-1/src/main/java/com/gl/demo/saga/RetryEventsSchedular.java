package com.gl.demo.saga;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.gl.demo.repo.EventsRetryModelRepository;

@Service
public class RetryEventsSchedular {

	@Autowired
	private EventsRetryModelRepository repo;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RetryEventsSchedular.class);

	/**
	 * Retry events.
	 *
	 * @throws PlatformException
	 *             the platform exception
	 */
	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	public void retryEvents() {
		List<EventsRetryModel> retryEventList = repo.findBydocTypeAndAcknowledged("RETRY_DOCUMENT", false);
		LOGGER.debug("In retryEvents with retryEventList {}", retryEventList);
		if (!retryEventList.isEmpty()) {
			ExecutorService executor = Executors.newFixedThreadPool(5);
			for (int i = 0; i < retryEventList.size(); i++) {
				Runnable worker = new RetryEventThread(retryEventList.get(i));
				executor.execute(worker);
			}
			executor.shutdown();
			while (!executor.isTerminated()) {
			}
			LOGGER.debug("Executor has terminated for retryEventList {}", retryEventList);
		}
	}

	/**
	 * The Class RetryEventThread.
	 */
	class RetryEventThread implements Runnable {

		/** The events retry model. */
		private EventsRetryModel eventsRetryModel;

		/**
		 * Instantiates a new retry event thread.
		 *
		 * @param eventsRetryModel
		 *            the events retry model
		 */
		public RetryEventThread(EventsRetryModel eventsRetryModel) {
			this.eventsRetryModel = eventsRetryModel;
		}

		public void run() {
			kafkaTemplate.send(this.eventsRetryModel.getEventType(), this.eventsRetryModel.getEventContext());
		}

		/**
		 * Gets the events retry model.
		 *
		 * @return the eventsRetryModel
		 */
		public EventsRetryModel getEventsRetryModel() {
			return eventsRetryModel;
		}

		/**
		 * Sets the events retry model.
		 *
		 * @param eventsRetryModel
		 *            the eventsRetryModel to set
		 */
		public void setEventsRetryModel(EventsRetryModel eventsRetryModel) {
			this.eventsRetryModel = eventsRetryModel;
		}

	}

}
