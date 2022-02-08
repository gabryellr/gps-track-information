package com.gabryellr.gpstrack.information.adapter.outbound.database.mongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Optional;

@Configuration
@EnableMongoAuditing(dateTimeProviderRef = "offsetDateTimeProvider")
public class Config {

    @Bean
    public DateTimeProvider offsetDateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(
            Arrays.asList(new ReadingConverterConfig(), new WritingConverterConfig())
        );
    }

}

