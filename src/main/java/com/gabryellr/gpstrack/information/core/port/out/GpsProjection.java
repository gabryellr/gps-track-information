package com.gabryellr.gpstrack.information.core.port.out;

import com.gabryellr.gpstrack.information.adapter.inbound.model.Query;
import com.gabryellr.gpstrack.information.core.model.GpsInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GpsProjection {

    void save(GpsInformation gpsInformation);

    Page<GpsInformation> findByQuery(Query query, Pageable pageable);

}