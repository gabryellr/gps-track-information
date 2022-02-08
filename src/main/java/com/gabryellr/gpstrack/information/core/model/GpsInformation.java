package com.gabryellr.gpstrack.information.core.model;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Builder
@Data
public class GpsInformation {

    private OffsetDateTime timestamp;
    private Integer lineId;
    private Integer direction;
    private String journeyPatternId;
    private OffsetDateTime timeFrame;
    private Integer vehicleJourneyID;
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
