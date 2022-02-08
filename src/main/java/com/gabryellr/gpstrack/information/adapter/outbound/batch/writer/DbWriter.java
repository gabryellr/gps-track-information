package com.gabryellr.gpstrack.information.adapter.outbound.batch.writer;

import com.gabryellr.gpstrack.information.core.model.GpsInformation;
import com.gabryellr.gpstrack.information.core.port.out.GpsProjection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DbWriter implements ItemWriter<GpsInformation> {

    private GpsProjection projection;

    public DbWriter(GpsProjection projection) {
        this.projection = projection;
    }

    @Override
    public void write(List<? extends GpsInformation> gpsInformationList) throws Exception {
        gpsInformationList.forEach(this.projection::save);
    }

}