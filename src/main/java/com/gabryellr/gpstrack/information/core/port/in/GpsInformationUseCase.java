package com.gabryellr.gpstrack.information.core.port.in;

import com.gabryellr.gpstrack.information.adapter.inbound.model.Query;
import com.gabryellr.gpstrack.information.core.model.GpsInformation;
import org.springframework.data.domain.Page;

public interface GpsInformationUseCase {

    Page<GpsInformation> listByQuery(Query query);

}