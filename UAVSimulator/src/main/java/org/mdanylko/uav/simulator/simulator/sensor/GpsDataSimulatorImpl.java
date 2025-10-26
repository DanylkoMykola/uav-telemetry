package org.mdanylko.uav.simulator.simulator.sensor;

import org.mdanylko.uav.simulator.sensor.GPS;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GpsDataSimulatorImpl implements GpsDataSimulator {

    private GPS gps;
    private final Random rnd = new Random();
    // Earth radius in meters
    private static final double R = 6_371_000;

    public GpsDataSimulatorImpl() {
        this.gps = new GPS(49.8397, 24.0297, 350.0, 12.5, 45.0);
    }

    public GpsDataSimulatorImpl(GPS gps) {
        this.gps = gps;
    }

    @Override
    public GPS getGps() {
        return gps;
    }

    @Override
    public void setGps(GPS gps) {
        this.gps = gps;
    }

    @Override
    public void generateGps(long intervalMs) {
        simulateGPS(this.gps, intervalMs);
    }

    private void simulateGPS(GPS gps, long intervalMs) {
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
