package com.gabryellr.gpstrack.information.adapter.outbound.database.mongodb.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@ReadingConverter
public class ReadingConverterConfig implements Converter<Long, OffsetDateTime> {

    @Override
    public OffsetDateTime convert(Long source) {
        return Instant.ofEpochMilli(source).atOffset(ZoneOffset.UTC);
    }
}

