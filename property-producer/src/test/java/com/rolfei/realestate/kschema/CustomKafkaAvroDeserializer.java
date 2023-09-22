package com.rolfei.realestate.kschema;

import com.rolfei.realestate.kschema.HouseSchema;
import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.avro.Schema;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * This code is not thread safe and should not be used in production environment
 */
public class CustomKafkaAvroDeserializer extends KafkaAvroDeserializer {

    @Autowired
    private HouseSchema houseSchema;

    @Override
    public Object deserialize(String topic, byte[] bytes) {
        if (topic.equals("create-house-avro")) {
            this.schemaRegistry = getMockClient(houseSchema.getHouseSchema());
        }

        return super.deserialize(topic, bytes);
    }



    private static SchemaRegistryClient getMockClient(final Schema schema$) {
        return new MockSchemaRegistryClient() {

            public synchronized Schema getById(int id) {
                return schema$;
            }
        };
    }
}
