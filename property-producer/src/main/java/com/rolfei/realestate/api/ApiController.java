package com.rolfei.realestate.api;


import com.rolfei.realestate.config.AppConfigProperties;
import com.rolfei.realestate.kschema.HouseSchema;
import com.rolfei.realestate.model.House;
import com.rolfei.realestate.producer.HouseAvroProducer;
import org.apache.avro.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0")

public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    final RestTemplate restTemplate;

    @Autowired
    private final AppConfigProperties properties;

    @Autowired
    private HouseAvroProducer houseAvroProducer;

    @Value("${kafka-producer.createHouseTopic}")
    private String topic;

    @Autowired
    private HouseSchema houseSchema;

    public ApiController(final RestTemplate restTemplate, AppConfigProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
        logger.info("bootstrap property" +properties.getBootstrapServers());
    }

    @GetMapping("/getHouses/{userId}")
    public List getHouses (@PathVariable String userId) {

        return new ArrayList();
    }

    @PostMapping(value ="/createHouse", consumes="application/json", produces="application/json")
    public List createHouse(@RequestBody House house) {
        Schema schema=houseSchema.getHouseSchema();
        logger.info("creating house");
        houseAvroProducer.send(topic,houseSchema.getHouseGenericRecord(house,schema));
        return new ArrayList();
    }

}