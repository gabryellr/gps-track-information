package com.gabryellr.gpstrack.information.adapter.outbound.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GpsInformationDto {

    private String timestamp;
    private String lineId;
    private String direction;
    private String journeyPatternId;
    private String timeFrame;
    private String vehicleJourneyID;
    private String busOperator;
    private String isCongestion;
    private String longitude;
    private String latitude;
    private String delay;
    private String blockId;
    private String vehicleId;
    private String stopId;
    private String isAtStop;

    public static String[] fields() {
        return new String[] {
            "timestamp", "lineId", "direction", "journeyPatternId", "timeFrame", "vehicleJourneyID", "busOperator",
            "isCongestion", "longitude", "latitude", "delay", "blockId", "vehicleId", "stopId", "isAtStop"};
    }

}
