package org.mdanylko.uav.simulator.generator.impl;

import org.mdanylko.uav.simulator.entity.GPS;
import org.mdanylko.uav.simulator.entity.Telemetry;
import org.mdanylko.uav.simulator.generator.UAVDataSimulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UAVDataSimulatorImpl implements UAVDataSimulator {

    private static final Logger log = LoggerFactory.getLogger(UAVDataSimulatorImpl.class);

    // Earth radius in meters
    private static final double R = 6_371_000;

    @Override
    public void generateTelemetry() {
        GPS gps = getGPSData();
        int iterations = 300;
        int counter = 0;
        long intervalMs = 1000;

        while (iterations > counter) {
            try {
                Telemetry telemetry = new Telemetry(LocalDateTime.now());
                telemetry.setGps(gps);


                log.info("Telemetry: {}", telemetry);

                simulateGPS(gps, intervalMs);

                Thread.sleep(intervalMs);
                counter++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void simulateGPS(GPS gps, long intervalMs) {
        Random rnd = new Random();
        // add small random jitter (sensor noise)
        double jitterLat = (rnd.nextDouble() - 0.5) * 1e-5; // ~1 meter-level
        double jitterLon = (rnd.nextDouble() - 0.5) * 1e-5;
        double jitterAlt = (rnd.nextDouble() - 0.5) * 0.5; // +-0.25 m

        // emit the current position (with jitter)
        gps.setLat(gps.getLat() + jitterLat);
        gps.setLon(gps.getLon() + jitterLon);
        gps.setAlt(gps.getAlt() + jitterAlt);


        double distance = gps.getSpeedMps() * (intervalMs / 1000.0); // meters per tick
        double[] next = destinationPoint(gps.getLat(), gps.getLon(), gps.getBearingDeg(), distance);
        gps.setLat(next[0]);
        gps.setLon(next[1]);
        // small alt change to simulate climb/descent
        gps.setAlt(gps.getAlt() + ((rnd.nextDouble() - 0.5) * 0.2));
    }

    private GPS getGPSData() {
        return new GPS(49.8397, 24.0297, 350.0, 12.5, 45.0);
    }

    /**
     * Computes destination point given start lat/lon (degrees), bearing (degrees),
     * and distance (meters). Returns [latDeg, lonDeg].
     */
    private static double[] destinationPoint(double latDeg, double lonDeg, double bearingDeg, double distanceMeters) {
        double lat = Math.toRadians(latDeg);
        double lon = Math.toRadians(lonDeg);
        double brng = Math.toRadians(bearingDeg);
        double dDivR = distanceMeters / R;

        double lat2 = Math.asin(Math.sin(lat) * Math.cos(dDivR) + Math.cos(lat) * Math.sin(dDivR) * Math.cos(brng));
        double lon2 = lon + Math.atan2(
                Math.sin(brng) * Math.sin(dDivR) * Math.cos(lat),
                Math.cos(dDivR) - Math.sin(lat) * Math.sin(lat2)
        );

        return new double[] { Math.toDegrees(lat2), Math.toDegrees(lon2) };
    }
}
