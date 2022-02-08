package com.gabryellr.gpstrack.information.adapter.inbound;

import com.gabryellr.gpstrack.information.adapter.inbound.model.GpsInformationOutputDto;
import com.gabryellr.gpstrack.information.adapter.inbound.model.Query;
import com.gabryellr.gpstrack.information.core.model.GpsInformation;

import java.time.OffsetDateTime;

public class MockInboundFactory {

    public static Query mockQuery() {
        return Query.builder()
                    .pages(1)
                    .quantitiesPerPage(10)
                    .endTime(OffsetDateTime.parse("2013-01-31T00:00:05Z"))
                    .vehicleId(123)
                    .busOperator("busOperatorId123")
                    .vehicleJourneyID(456)
                    .startTime(OffsetDateTime.parse("2013-03-31T00:00:05Z"))
                    .build();
    }

    public static GpsInformation mockGpsInformation() {
        return GpsInformation.builder()
                             .blockId(123)
                             .delay(456)
                             .direction(789)
                             .isAtStop(true)
                             .isCongestion(false)
                             .journeyPatternId("journeyPatternId123")
                             .latitude(2.0)
                             .longitude(1.0)
                             .lineId(101112)
                             .stopId(131415)
                             .timeFrame(OffsetDateTime.parse("2013-01-31T00:00:05Z"))
                             .timestamp(OffsetDateTime.parse("2013-01-31T00:00:05Z"))
                             .busOperator("busOperatorId123")
                             .vehicleId(161718)
                             .build();
    }

    public static GpsInformationOutputDto mockGpsInformationOutputDto() {
        return GpsInformationOutputDto.builder()
                                      .blockId(123)
                                      .delay(456)
                                      .direction(789)
                                      .isAtStop(true)
                                      .isCongestion(false)
                                      .journeyPatternId("journeyPatternId123")
                                      .latitude(2.0)
                                      .longitude(1.0)
                                      .lineId(101112)
                                      .stopId(131415)
                                      .timeFrame(OffsetDateTime.parse("2013-01-31T00:00:05Z"))
                                      .timestamp(OffsetDateTime.parse("2013-01-31T00:00:05Z"))
                                      .busOperator("busOperatorId123")
                                      .vehicleId(161718)
                                      .build();
    }
}
