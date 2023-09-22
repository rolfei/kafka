package com.rolfei.realestate.api;

import java.io.*;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;


import java.io.IOException;
import java.util.Properties;

public class ConfluentProduce {

    public static Properties loadConfig(final String configFile) throws IOException {

        final Properties cfg = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream(configFile)) {
            cfg.load(inputStream);
        }
        return cfg;
    }

    public static void main(String[] args) {
        final Properties props;
        try {
            props = loadConfig("client.properties");
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaJsonSerializer");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Producer<String, String> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<>("topic_0", "key", "value"));
        producer.close();
    }


}
