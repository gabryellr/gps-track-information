package com.gabryellr.gpstrack.information.adapter.outbound.database.mongodb.mapper;

import com.gabryellr.gpstrack.information.adapter.outbound.database.mongodb.MockOutboundDatabaseFactory;
import com.gabryellr.gpstrack.information.adapter.outbound.database.mongodb.model.GpsInformationDocument;
import com.gabryellr.gpstrack.information.core.model.GpsInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class DocumentMapperTest {

    private DocumentMapper mapper;

    @BeforeEach
    void setUp() {
        this.mapper = Mappers.getMapper(DocumentMapper.class);
    }

    @Test
    void toDocument_validInput_IsProperlyMapped() {
        GpsInformation gpsInformation = MockOutboundDatabaseFactory.mockGpsInformation();

        GpsInformationDocument expected = MockOutboundDatabaseFactory.mockGpsInformationDocument();
        GpsInformationDocument actual = this.mapper.toDocument(gpsInformation);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toGpsInformation_validInput_IsProperlyMapped() {
        GpsInformationDocument document = MockOutboundDatabaseFactory.mockGpsInformationDocument();

        GpsInformation expected = MockOutboundDatabaseFactory.mockGpsInformation();
        GpsInformation actual = this.mapper.toGpsInformation(document);

        Assertions.assertEquals(expected, actual);
    }
}