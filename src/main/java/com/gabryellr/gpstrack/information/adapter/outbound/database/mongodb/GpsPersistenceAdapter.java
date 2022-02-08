package com.gabryellr.gpstrack.information.adapter.outbound.database.mongodb;

import com.gabryellr.gpstrack.information.adapter.outbound.database.mongodb.mapper.DocumentMapper;
import com.gabryellr.gpstrack.information.adapter.outbound.database.mongodb.model.GpsInformationDocument;
import com.gabryellr.gpstrack.information.core.model.GpsInformation;
import com.gabryellr.gpstrack.information.core.port.out.GpsProjection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class GpsPersistenceAdapter implements GpsProjection {

    private DocumentMapper mapper;
    private MongoTemplate mongoTemplate;

    public GpsPersistenceAdapter(DocumentMapper mapper, MongoTemplate mongoTemplate) {
        this.mapper = mapper;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(GpsInformation gpsInformation) {
        log.info("Converting model {} into document", gpsInformation);
        GpsInformationDocument document = this.mapper.toDocument(gpsInformation);

        log.info("Document {} will be saved in the database", document);
        GpsInformationDocument documentSaved = mongoTemplate.save(document);

        log.info("document saved {}", documentSaved);
    }

    @Override
    public Page<GpsInformation> findByQuery(com.gabryellr.gpstrack.information.adapter.inbound.model.Query query, Pageable pageable) {
        Query mongoQuery = new Query().with(pageable);
        final List<Criteria> criteria = new ArrayList<>();

        Long startDateMilli = query.getStartTime() != null ? offSetToEpoch(query.getStartTime()) : null;
        Long endDateMilli = query.getEndTime() != null ? offSetToEpoch(query.getEndTime()) : null;

        if (startDateMilli != null && endDateMilli != null) {
            criteria.add(Criteria.where("timeFrame").gte(startDateMilli));
            criteria.add(Criteria.where("timeFrame").lte(endDateMilli));
        }

        if (query.getBusOperator() != null) {
            criteria.add(Criteria.where("busOperator").is(query.getBusOperator()));
        }

        if (query.getVehicleJourneyID() != null) {
            criteria.add(Criteria.where("vehicleJourneyID").is(query.getVehicleJourneyID()));
            criteria.add(Criteria.where("isAtStop").is(true));
        }

        if (query.getVehicleId() != null) {
            criteria.add(Criteria.where("vehicleId").is(query.getVehicleId()));
        }
        if (!criteria.isEmpty()) {
            mongoQuery.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        }

        Page<GpsInformationDocument> page = PageableExecutionUtils.getPage(mongoTemplate.find(mongoQuery, GpsInformationDocument.class), pageable,
                                                                           () -> mongoTemplate.count(mongoQuery.skip(0).limit(0),
                                                                                                     GpsInformationDocument.class));

        return page.map(this.mapper::toGpsInformation);
    }

    private Long offSetToEpoch(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toInstant().toEpochMilli();
    }

}