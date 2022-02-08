package com.gabryellr.gpstrack.information.adapter.inbound.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Builder
@Data
public class GpsInformationOutputDto {

    @JsonProperty("fleet")
    private Integer vehicleJourneyID;

    private OffsetDateTime timestamp;
    private Integer lineId;
    private Integer direction;
    private String journeyPatternId;
    private OffsetDateTime timeFrame;
    private String busOperator;
    private Boolean isCongestion;
    private Double latitude;
    private Double longitude;
    private Integer delay;
    private Integer blockId;
    private Integer vehicleId;
    private Integer stopId;
    private Boolean isAtStop;

}