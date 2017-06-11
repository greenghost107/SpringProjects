package com.example.Transportation.config;

import com.example.Transportation.util.CustomDateTimeDeserializer;
import com.example.Transportation.util.CustomDateTimeSerializer;
import com.example.Transportation.util.CustomLocalDateSerializer;
import com.example.Transportation.util.ISO8601LocalDateDeserializer;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Michael on 6/11/2017.
 */
@Configuration
public class JacksonConfiguration {

    @Bean
    public JodaModule jacksonJodaModule() {
        JodaModule module = new JodaModule();
        module.addSerializer(DateTime.class, new CustomDateTimeSerializer());
        module.addDeserializer(DateTime.class, new CustomDateTimeDeserializer());
        module.addSerializer(LocalDate.class, new CustomLocalDateSerializer());
        module.addDeserializer(LocalDate.class, new ISO8601LocalDateDeserializer());
        return module;
    }
}
