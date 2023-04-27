package org.example;

import io.smallrye.reactive.messaging.kafka.Record;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KtableConsumer {

    private final Logger logger = LoggerFactory.getLogger(KtableConsumer.class);
    @Inject
    SynchronizedStateStore synchronizedStateStore;

    @Incoming("ktable")
    public void sink(Record<String, String> record) {
        logger.info("Received: ( key:" + record.key() + ", value:" + record.value() + " )");
        if (record.key() != null && record.value() != null) {
            synchronizedStateStore.append(record.key(), record.value());
            logger.info("Appended: ( key:" + record.key() + ", value:" + record.value() + " ) to state store");
        } else {
            logger.info("Key or Value was null: ( key:" + record.key() + ", value:" + record.value() + " ), not adding to store");
        }
    }
}
