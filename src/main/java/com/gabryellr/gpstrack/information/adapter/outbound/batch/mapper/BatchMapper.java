package com.gabryellr.gpstrack.information.adapter.outbound.batch.mapper;

import com.gabryellr.gpstrack.information.adapter.outbound.batch.model.GpsInformationDto;
import com.gabryellr.gpstrack.information.core.model.GpsInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Mapper(componentModel = "spring")
public interface BatchMapper {

    int HOURS_AHEAD = 1;

    @Mapping(target = "timestamp", source = "timestamp", qualifiedByName = "toTimeStamp")
    @Mapping(target = "timeFrame", source = "timeFrame", qualifiedByName = "toTimeFrame")
    @Mapping(target = "isCongestion", source = "isCongestion", qualifiedByName = "toBoolean")
    @Mapping(target = "isAtStop", source = "isAtStop", qualifiedByName = "toBoolean")
    GpsInformation toGpsInformation(GpsInformationDto input);

    @Named("toBoolean")
    default Boolean toBoolean(Integer option) {
        return option == 1;
    }

    @Named("toTimeFrame")
    default OffsetDateTime toTimeFrame(String timeFrame) {
        if (timeFrame == null) {
            return null;
        }
        LocalDateTime localDateTime = LocalDate.parse(timeFrame, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(6, 0);
        return OffsetDateTime.of(localDateTime, ZoneOffset.UTC);
    }

    @Named("toTimeStamp")
    default OffsetDateTime toTimeStamp(String timeStamp) {
        if (timeStamp == null) {
            return null;
        }
        Instant instant = Instant.EPOCH.plus(Long.parseLong(timeStamp), ChronoUnit.MICROS);
        return instant.atOffset(ZoneOffset.ofHours(HOURS_AHEAD));
    }
}
