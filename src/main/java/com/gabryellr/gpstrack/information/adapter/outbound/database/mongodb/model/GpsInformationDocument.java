package com.gabryellr.gpstrack.information.adapter.outbound.database.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GpsInformationDocument {

    @Id
    private String id;

    private OffsetDateTime timestamp;
    private Integer lineId;
    private Integer direction;
    private String journeyPatternId;
    private OffsetDateTime timeFrame;
    private Integer vehicleJourneyID;
    private String busOperator;
    private Boolean isCongestion;
    private Double longitude;
    private Double latitude;
    private Integer delay;
    private Integer blockId;
    private Integer vehicleId;
    private Integer stopId;
    private Boolean isAtStop;

}