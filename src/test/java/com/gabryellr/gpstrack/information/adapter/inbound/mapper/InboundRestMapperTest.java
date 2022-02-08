package com.gabryellr.gpstrack.information.adapter.inbound.mapper;

import com.gabryellr.gpstrack.information.adapter.inbound.MockInboundFactory;
import com.gabryellr.gpstrack.information.adapter.inbound.model.GpsInformationOutputDto;
import com.gabryellr.gpstrack.information.adapter.inbound.model.Query;
import com.gabryellr.gpstrack.information.core.model.GpsInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.OffsetDateTime;

class InboundRestMapperTest {

    InboundRestMapper mapper;

    @BeforeEach
    void setUp() {
        this.mapper = Mappers.getMapper(InboundRestMapper.class);
    }

    @Test
    void toQuery_validInput_IsProperlyMapped() {
        Query expected = MockInboundFactory.mockQuery();

        Query actual = this.mapper.toQuery("2013-03-31T00:00:05Z", "2013-01-31T00:00:05Z", expected.getBusOperator(),
                                           expected.getVehicleJourneyID(), expected.getVehicleId(), expected.getPages(),
                                           expected.getQuantitiesPerPage());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toOffsetDateTime_validInput_ShouldReturnOffsetDateTime() {
        OffsetDateTime expected = OffsetDateTime.parse("2013-03-31T00:00:05Z");

        OffsetDateTime actual = this.mapper.toOffsetDateTime("2013-03-31T00:00:05Z");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toOffsetDateTime_invalidInput_ShouldReturnNull() {
        Assertions.assertNull(this.mapper.toOffsetDateTime("2075-02-31T00:00:05Z"));
    }

    @Test
    void toGpsInformationOutputDto_validInput_IsProperlyMapped() {
        GpsInformation gpsInformation = MockInboundFactory.mockGpsInformation();
        GpsInformationOutputDto expected = MockInboundFactory.mockGpsInformationOutputDto();

        GpsInformationOutputDto actual = this.mapper.toGpsInformationOutputDto(gpsInformation);

        Assertions.assertEquals(expected, actual);
    }

}