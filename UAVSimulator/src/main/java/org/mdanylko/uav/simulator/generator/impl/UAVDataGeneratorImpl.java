package org.mdanylko.uav.simulator.generator.impl;

import org.mdanylko.uav.simulator.generator.sensor.GpsDataGenerator;
import org.mdanylko.uav.simulator.sensor.Attitude;
import org.mdanylko.uav.simulator.sensor.Telemetry;
import org.mdanylko.uav.simulator.generator.UAVDataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UAVDataGeneratorImpl implements UAVDataGenerator {

    private static final Logger log = LoggerFactory.getLogger(UAVDataGeneratorImpl.class);
    private final GpsDataGenerator gpsGenerator;

    private Attitude attitude;

    public UAVDataGeneratorImpl(GpsDataGenerator gpsGenerator) {
       this.gpsGenerator = gpsGenerator;
    }

    @Override
    public void generateTelemetry() {
        int iterations = 300;
        int counter = 0;
        long intervalMs = 1000;

        while (iterations > counter) {
            try {
                Telemetry telemetry = new Telemetry(LocalDateTime.now());
                telemetry.setGps(gpsGenerator.getGps());


                log.info("Telemetry: {}", telemetry);

                gpsGenerator.generateGps(intervalMs);

                Thread.sleep(intervalMs);
                counter++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Attitude getAttitude() {
        return new Attitude(0.0, 0.0, 0.0);
    }
}
