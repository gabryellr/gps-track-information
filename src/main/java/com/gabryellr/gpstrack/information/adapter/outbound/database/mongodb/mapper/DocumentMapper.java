package com.gabryellr.gpstrack.information.adapter.outbound.database.mongodb.mapper;

import com.gabryellr.gpstrack.information.adapter.outbound.database.mongodb.model.GpsInformationDocument;
import com.gabryellr.gpstrack.information.core.model.GpsInformation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    GpsInformationDocument toDocument(GpsInformation gpsInformation);

    GpsInformation toGpsInformation(GpsInformationDocument gpsInformationDocument);

}