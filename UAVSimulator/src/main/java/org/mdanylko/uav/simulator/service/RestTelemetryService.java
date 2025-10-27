package org.mdanylko.uav.simulator.service;

import org.mdanylko.uav.simulator.config.IngestServiceProperties;
import org.mdanylko.uav.simulator.sensor.Telemetry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTelemetryService implements TelemetryService {

    private final RestTemplate restTemplate;
    private final IngestServiceProperties inSerProp;

    public RestTelemetryService(RestTemplate restTemplate, IngestServiceProperties inSerProp) {
        this.restTemplate = restTemplate;
        this.inSerProp = inSerProp;
    }

    @Override
    public void sendTelemetry(Telemetry telemetry) {
        String url = inSerProp.getBaseUrl();
        restTemplate.postForEntity(url, telemetry, Void.class);
    }
}
