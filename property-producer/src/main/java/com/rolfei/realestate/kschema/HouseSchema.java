package com.rolfei.realestate.kschema;

import com.rolfei.realestate.model.House;
import org.apache.avro.Schema;
import org.springframework.stereotype.Component;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
@Component
public class HouseSchema {

    public HouseSchema() {

    }

    public Schema getHouseSchema() {
        String houseSchema = "{\"type\": \"record\"," +
                "\"name\" : \"house\"," +
                "\"fields\" : [{\"name\" : \"id\", \"type\":\"int\"},"+
                "{\"name\" : \"address\", \"type\":\"string\"},"+
                "{\"name\" : \"purpose\", \"type\":\"string\" },"+
                "{\"name\" : \"country\", \"type\":\"string\" , \"default\" : \"None\" }"+

                "]}";
   
    Schema.Parser parser = new Schema.Parser();
    return parser.parse(houseSchema);
    }

    public GenericRecord getHouseGenericRecord(House house, Schema schema) {
        GenericRecord genericRecord= new GenericData.Record(schema);
        genericRecord.put("id",house.getId());
        genericRecord.put("address",house.getAddress());
        genericRecord.put("purpose",house.getPurpose());
        genericRecord.put("country",house.getCountry());
        return genericRecord;
    }
}
