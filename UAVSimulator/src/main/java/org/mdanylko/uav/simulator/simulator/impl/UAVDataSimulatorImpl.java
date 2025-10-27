package org.mdanylko.uav.simulator.simulator.impl;

import org.mdanylko.uav.simulator.sensor.Status;
import org.mdanylko.uav.simulator.service.TelemetryService;
import org.mdanylko.uav.simulator.simulator.sensor.AttitudeDataSimulator;
import org.mdanylko.uav.simulator.simulator.sensor.BatterySimulator;
import org.mdanylko.uav.simulator.simulator.sensor.GpsDataSimulator;
import org.mdanylko.uav.simulator.sensor.Telemetry;
import org.mdanylko.uav.simulator.simulator.UAVDataSimulator;
import org.mdanylko.uav.simulator.simulator.sensor.VelocitySimulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UAVDataSimulatorImpl implements UAVDataSimulator {

    private static final Logger log = LoggerFactory.getLogger(UAVDataSimulatorImpl.class);
    private final GpsDataSimulator gpsSimulator;
    private final AttitudeDataSimulator attitudeSimulator;
    private final VelocitySimulator velocitySimulator;
    private final BatterySimulator batterySimulator;
    private final Status status;
    private TelemetryService service;


    public UAVDataSimulatorImpl(GpsDataSimulator gpsSimulator, AttitudeDataSimulator attitudeSimulator, VelocitySimulator velocitySimulator, BatterySimulator batterySimulator, TelemetryService service) {
       this.gpsSimulator = gpsSimulator;
        this.attitudeSimulator = attitudeSimulator;
        this.velocitySimulator = velocitySimulator;
        this.batterySimulator = batterySimulator;
        this.service = service;
        this.status = new Status(true, false);
    }

    @Override
    public void generateTelemetry() {
        int iterations = 300;
        int counter = 0;
        long intervalMs = 1000;
        Telemetry telemetry = new Telemetry(UUID.randomUUID().toString());

        while (iterations > counter) {
            try {
                telemetry.setTimestamp(LocalDateTime.now());
                telemetry.setGps(gpsSimulator.getGps());
                telemetry.setAttitude(attitudeSimulator.getAttitude());
                telemetry.setVelocity(velocitySimulator.getVelocity());
                telemetry.setBattery(batterySimulator.getBattery());
                telemetry.setStatus(status);


                log.info("Telemetry: {}", telemetry);
                service.sendTelemetry(telemetry);

                gpsSimulator.generateGps(intervalMs);
                attitudeSimulator.simulateAtt(intervalMs);
                velocitySimulator.simulateVel(intervalMs, gpsSimulator.getGps());
                batterySimulator.simulateBattery();

                Thread.sleep(intervalMs);
                counter++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
