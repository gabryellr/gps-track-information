package com.gabryellr.gpstrack.information.core;

import com.gabryellr.gpstrack.information.adapter.inbound.model.Query;
import com.gabryellr.gpstrack.information.core.model.GpsInformation;
import com.gabryellr.gpstrack.information.core.port.in.GpsInformationUseCase;
import com.gabryellr.gpstrack.information.core.port.out.GpsProjection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GpsInformationAdapter implements GpsInformationUseCase {

    private GpsProjection projection;

    public GpsInformationAdapter(GpsProjection projection) {
        this.projection = projection;
    }

    @Override
    public Page<GpsInformation> listByQuery(Query query) {
        int pages = query.getPages() != null ? query.getPages() : 0;
        int quantitiesPerPage = query.getQuantitiesPerPage() != null ? query.getQuantitiesPerPage() : 20;

        Pageable pageable = PageRequest.of(pages, quantitiesPerPage, Sort.Direction.ASC, "timestamp");

        return projection.findByQuery(query, pageable);
    }

}