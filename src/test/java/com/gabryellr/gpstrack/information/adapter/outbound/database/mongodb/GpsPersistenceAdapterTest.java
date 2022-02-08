package com.gabryellr.gpstrack.information.adapter.outbound.database.mongodb;

import com.gabryellr.gpstrack.information.adapter.inbound.model.Query;
import com.gabryellr.gpstrack.information.adapter.outbound.database.mongodb.mapper.DocumentMapper;
import com.gabryellr.gpstrack.information.adapter.outbound.database.mongodb.model.GpsInformationDocument;
import com.gabryellr.gpstrack.information.core.model.GpsInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

class GpsPersistenceAdapterTest {

    @Mock
    private DocumentMapper mapper;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private GpsPersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save_validInput_CallMongoTemplateSaveMethodAtLeastOnce() {
        GpsInformation gpsInformation = Mockito.mock(GpsInformation.class);
        GpsInformationDocument document = Mockito.mock(GpsInformationDocument.class);

        Mockito.when(mapper.toDocument(ArgumentMatchers.any())).thenReturn(document);
        Mockito.when(mongoTemplate.save(ArgumentMatchers.any())).thenReturn(document);

        this.adapter.save(gpsInformation);

        Mockito.verify(mongoTemplate, Mockito.atLeastOnce()).save(ArgumentMatchers.any());
    }

    @Test
    void findByQuery_givenAllArguments_CallMongoTemplateFindMethodAtLeastOnce() {
        Query query = MockOutboundDatabaseFactory.mockQuery();
        PageRequest pageRequest = PageRequest.of(query.getPages(), query.getQuantitiesPerPage(), Sort.Direction.ASC, "timestamp");

        this.adapter.findByQuery(query, pageRequest);

        Mockito.verify(mongoTemplate, Mockito.atLeastOnce()).find(ArgumentMatchers.any(org.springframework.data.mongodb.core.query.Query.class),
                                                                  ArgumentMatchers.eq(GpsInformationDocument.class));
    }
}