package com.gabryellr.gpstrack.information.adapter.inbound.model;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Builder
@Data
public class Query {

    Integer vehicleJourneyID;
    OffsetDateTime startTime;
    OffsetDateTime endTime;
    String busOperator;
    Integer vehicleId;
    Integer pages;
    Integer quantitiesPerPage;

}