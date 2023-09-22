package com.rolfei.realestate.producer;

import com.rolfei.realestate.kschema.HouseSchema;
import com.rolfei.realestate.model.House;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class HouseAvroProducer {

    private static final Logger LOGGER= LoggerFactory.getLogger(HouseAvroProducer.class);
    private String key="1";

    @Autowired
    private KafkaTemplate<String, GenericRecord> kafkaTemplate;

    @Autowired
    private HouseSchema houseSchema;

    public void send(String topic, GenericRecord payload) {
        LOGGER.info("Sending payload {} to topic {}" ,payload,topic);


        kafkaTemplate.send(topic, key,payload);
    }

    public void sendList(String topic, List <House> houseList) {

        LOGGER.info("Sending list payload  topic {}" ,topic);
        houseList.stream().map(v -> kafkaTemplate.send(topic, key,houseSchema.getHouseGenericRecord(v,houseSchema.getHouseSchema())));
    }
}
