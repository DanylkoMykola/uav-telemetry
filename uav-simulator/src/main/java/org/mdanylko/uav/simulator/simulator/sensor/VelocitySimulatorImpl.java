package org.mdanylko.uav.simulator.simulator.sensor;

import org.mdanylko.uav.simulator.constant.Constants;
import org.mdanylko.uav.simulator.sensor.GPS;
import org.mdanylko.uav.simulator.sensor.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VelocitySimulatorImpl implements VelocitySimulator {

    private static final Logger log = LoggerFactory.getLogger(VelocitySimulatorImpl.class);

    private Velocity velocity;
    private GPS prevGps;

    public VelocitySimulatorImpl() {
        this.velocity = new Velocity(0.0, 0.0, 0.0);
    }

    public VelocitySimulatorImpl(Velocity velocity) {
        this.velocity = velocity;
    }

    @Override
    public void simulateVel(long intervalMs, GPS newGps) {
        if (newGps == null) {
            return;
        } else if (prevGps == null) {
            this.prevGps = newGps.clone();
        }
        double deltaTime = intervalMs / 1000.0;
        if (deltaTime <= 0) {
            this.velocity = new Velocity(0, 0, 0);
        }

        double horizontalDist = haversine(
                this.prevGps.getLat(), this.prevGps.getLon(),
                newGps.getLat(), newGps.getLon()
        );

        double deltaZ = newGps.getAlt() - prevGps.getAlt();
        double totalDist = Math.sqrt(horizontalDist * horizontalDist + deltaZ * deltaZ);

        double speed = totalDist / deltaTime;
        double verticalSpeed = deltaZ / deltaTime;
        double bearing = bearing(
                this.prevGps.getLat(), this.prevGps.getLon(),
                newGps.getLat(), newGps.getLon()
        );

        this.prevGps = newGps.clone();

        log.debug("Speed: {:.2f} m/s | Vertical speed: {:.2f} m/s | Bearing: {:.2f}°",
                speed, verticalSpeed, bearing);

        this.velocity = new Velocity(speed, bearing, verticalSpeed);

    }

    @Override
    public Velocity getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.pow(Math.sin(dLat / 2), 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.pow(Math.sin(dLon / 2), 2);

        return 2 * Constants.EARTH_RADIUS * Math.asin(Math.sqrt(a));
    }

    private double bearing(double lat1, double lon1, double lat2, double lon2) {
        double dLon = Math.toRadians(lon2 - lon1);
        double y = Math.sin(dLon) * Math.cos(Math.toRadians(lat2));
        double x = Math.cos(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                - Math.sin(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(dLon);

        return (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
    }
}
