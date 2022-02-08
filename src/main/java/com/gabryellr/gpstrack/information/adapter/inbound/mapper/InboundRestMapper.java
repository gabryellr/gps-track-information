package com.gabryellr.gpstrack.information.adapter.inbound.mapper;

import com.gabryellr.gpstrack.information.adapter.inbound.model.GpsInformationOutputDto;
import com.gabryellr.gpstrack.information.adapter.inbound.model.Query;
import com.gabryellr.gpstrack.information.core.model.GpsInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

@Mapper(componentModel = "spring")
public interface InboundRestMapper {

    default Query toQuery(String startTime, String endTime, String operator, Integer fleet, Integer vehicle, Integer page, Integer size) {
        return Query.builder()
                    .startTime(startTime != null ? toOffsetDateTime(startTime) : null)
                    .endTime(endTime != null ? toOffsetDateTime(endTime) : null)
                    .busOperator(operator)
                    .vehicleJourneyID(fleet)
                    .vehicleId(vehicle)
                    .quantitiesPerPage(size)
                    .pages(page)
                    .build();
    }

    @Named("toOffsetDateTime")
    default OffsetDateTime toOffsetDateTime(String time) {
        try {
            return OffsetDateTime.parse(time);
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();

            return null;
        }
    }

    GpsInformationOutputDto toGpsInformationOutputDto(GpsInformation gpsInformation);

}