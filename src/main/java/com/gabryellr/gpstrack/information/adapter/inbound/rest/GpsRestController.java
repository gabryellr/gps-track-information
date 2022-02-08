package com.gabryellr.gpstrack.information.adapter.inbound.rest;

import com.gabryellr.gpstrack.information.adapter.inbound.mapper.InboundRestMapper;
import com.gabryellr.gpstrack.information.adapter.inbound.model.GpsInformationOutputDto;
import com.gabryellr.gpstrack.information.adapter.inbound.model.Query;
import com.gabryellr.gpstrack.information.core.port.in.GpsInformationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/gpstrack")
public class GpsRestController {

    private InboundRestMapper mapper;
    private GpsInformationUseCase useCase;

    public GpsRestController(InboundRestMapper mapper, GpsInformationUseCase useCase) {
        this.mapper = mapper;
        this.useCase = useCase;
    }

    @Operation(summary = "Get a list of vehicles according the filter")
    @ApiResponse(responseCode = "200",
        content = @Content(mediaType = "application/json"))
    @GetMapping
    public ResponseEntity<Page<GpsInformationOutputDto>> listAll(@RequestParam(required = false) String startTime,
        @RequestParam(required = false) String endTime, @RequestParam(required = false) String operatorId,
        @RequestParam(required = false) Integer fleet,
        @RequestParam(required = false) Integer vehicleId, @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size) {

        Query query = this.mapper.toQuery(startTime, endTime, operatorId, fleet, vehicleId, page, size);

        Page<GpsInformationOutputDto> pageOutput = this.useCase.listByQuery(query).map(this.mapper::toGpsInformationOutputDto);

        return ResponseEntity.ok(pageOutput);
    }

}
