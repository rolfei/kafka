package com.rolfei.realestate.producer;

import com.rolfei.realestate.kschema.HouseSchema;
import com.rolfei.realestate.model.House;
import com.rolfei.realestate.producer.HouseAvroProducer;
import org.apache.avro.Schema;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.cloud.azure.appconfiguration.enabled=false")
@ActiveProfiles("test")
@DirtiesContext
@EmbeddedKafka(partitions=1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092","port=9092"},bootstrapServersProperty = "localhost:9092")
class HouseAvroProducerTest {

    @Autowired
    private HouseAvroProducer houseAvroProducer;

    @Autowired
    private HouseSchema houseSchema;

    @Value("${kafka-producer.createHouseTopic}")
    String topic;

   @Test
    public void producerTest() throws Exception {
        House house = new House();
        house.setId(1);
        house.setAddress("testaddress");
        house.setPurpose("purpose");
        house.setCountry("UK");
        Schema schema=houseSchema.getHouseSchema();
        houseAvroProducer.send(topic,houseSchema.getHouseGenericRecord(house,schema));
    }
}