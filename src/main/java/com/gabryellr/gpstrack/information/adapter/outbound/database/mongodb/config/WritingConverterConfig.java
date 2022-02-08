package com.gabryellr.gpstrack.information.adapter.outbound.database.mongodb.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.OffsetDateTime;

@WritingConverter
public class WritingConverterConfig implements Converter<OffsetDateTime, Long> {

    @Override
    public Long convert(OffsetDateTime source) {
        return source.toInstant().toEpochMilli();
    }

}