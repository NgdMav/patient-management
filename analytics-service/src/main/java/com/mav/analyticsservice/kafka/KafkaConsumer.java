package com.mav.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaConsumer {
    
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
    
    // group id is configured via spring.kafka.consumer.group-id (docker-compose env can override)
    @KafkaListener(topics = "patient")
    public void consumeEvent(byte[] event) {
        log.debug("Consuming message, size={} bytes", event != null ? event.length : 0);
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            // perform any analytics

            log.info("Received Patient Event: [PatientId={},PatientName={},PatientEmail={}]",
                    patientEvent.getPatientId(),
                    patientEvent.getName(),
                    patientEvent.getEmail());
        } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing event: {}", e.getMessage(), e);
        }
    }
}
