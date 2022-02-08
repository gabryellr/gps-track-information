package com.gabryellr.gpstrack.information.adapter.outbound.batch.processor;

import com.gabryellr.gpstrack.information.adapter.outbound.batch.mapper.BatchMapper;
import com.gabryellr.gpstrack.information.adapter.outbound.batch.model.GpsInformationDto;
import com.gabryellr.gpstrack.information.core.model.GpsInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Processor implements ItemProcessor<GpsInformationDto, GpsInformation> {

    private BatchMapper mapper;

    private Processor(BatchMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public GpsInformation process(GpsInformationDto input) throws Exception {
        GpsInformation gpsInformation = this.mapper.toGpsInformation(input);
        log.info("Object converted from input to model: {}", gpsInformation);

        return gpsInformation;
    }

}