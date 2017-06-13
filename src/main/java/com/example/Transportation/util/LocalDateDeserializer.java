package com.example.Transportation.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Michael on 6/12/2017.
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JsonProcessingException {
        System.out.println("deserialize " + arg0.getText());
        return LocalDate.parse(arg0.getText());
    }
}
