package org.mdanylko.uav.simulator.simulator.impl;

import org.mdanylko.uav.simulator.simulator.sensor.AttitudeDataSimulator;
import org.mdanylko.uav.simulator.simulator.sensor.GpsDataSimulator;
import org.mdanylko.uav.simulator.sensor.Telemetry;
import org.mdanylko.uav.simulator.simulator.UAVDataSimulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UAVDataSimulatorImpl implements UAVDataSimulator {

    private static final Logger log = LoggerFactory.getLogger(UAVDataSimulatorImpl.class);
    private final GpsDataSimulator gpsSimulator;
    private final AttitudeDataSimulator attitudeSimulator;


    public UAVDataSimulatorImpl(GpsDataSimulator gpsSimulator, AttitudeDataSimulator attitudeSimulator) {
       this.gpsSimulator = gpsSimulator;
        this.attitudeSimulator = attitudeSimulator;
    }

    @Override
    public void generateTelemetry() {
        int iterations = 300;
        int counter = 0;
        long intervalMs = 1000;

        while (iterations > counter) {
            try {
                Telemetry telemetry = new Telemetry(LocalDateTime.now());
                telemetry.setGps(gpsSimulator.getGps());
                telemetry.setAttitude(attitudeSimulator.getAttitude());


                log.info("Telemetry: {}", telemetry);

                gpsSimulator.generateGps(intervalMs);
                attitudeSimulator.simulateAtt(intervalMs);

                Thread.sleep(intervalMs);
                counter++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
