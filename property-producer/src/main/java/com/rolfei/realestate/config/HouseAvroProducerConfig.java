package com.rolfei.realestate.config;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class HouseAvroProducerConfig {

    private final AppConfigProperties properties;
    private String bootstrapServers;


    private String schemaRegistryUrl;

    public HouseAvroProducerConfig(AppConfigProperties properties) {
        this.properties = properties;
    }

    @Bean
    @Profile("!test")
    public ProducerFactory<String, GenericRecord> avroProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);

        configProps.put("schema.registry.url",properties.getSchemaRegistryUrl());
        configProps.put("security.protocol",properties.getSecurityProtocol());
        configProps.put("sasl.jaas.config",properties.getSaslJaasConfig());
        configProps.put("sasl.mechanism",properties.getSaslMechanism());
        configProps.put("client.dns.lookup",properties.getClientDnsLookup());
        configProps.put("session.timeout.ms",properties.getSessionTimeoutMs());
        configProps.put("basic.auth.credentials.source",properties.getBasicAuthCredentialsSource());
        configProps.put("basic.auth.user.info",properties.getBasicAuthUserInfo());




        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    @Profile("test")
    public ProducerFactory<String, GenericRecord> testAvroProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.rolfei.realestate.kschema.CustomKafkaAvroSerializer");
        configProps.put("schema.registry.url","http://not-used");
        return new DefaultKafkaProducerFactory<>(configProps);
    }



    @Bean
    @Profile("!test")
    public KafkaTemplate<String, GenericRecord> avroKafkaTemplate() {
        return new KafkaTemplate<>(avroProducerFactory());
    }

    @Bean
    @Profile("test")
    public KafkaTemplate<String, GenericRecord> testAvroKafkaTemplate() {
        return new KafkaTemplate<>(testAvroProducerFactory());
    }
}