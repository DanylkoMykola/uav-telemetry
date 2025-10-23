package org.mdanylko.uav.simulator.entity;

public class GPS {

    private double lat;
    private double lon;
    private double alt;
    private double speedMps;
    private double bearingDeg;

    public GPS(double lat, double lon, double alt, double speedMps, double bearingDeg) {
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
        this.speedMps = speedMps;
        this.bearingDeg = bearingDeg;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }

    public double getSpeedMps() {
        return speedMps;
    }

    public void setSpeedMps(double speedMps) {
        this.speedMps = speedMps;
    }

    public double getBearingDeg() {
        return bearingDeg;
    }

    public void setBearingDeg(double bearingDeg) {
        this.bearingDeg = bearingDeg;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GPS{");
        sb.append("lat=").append(lat);
        sb.append(", lon=").append(lon);
        sb.append(", alt=").append(alt);
        sb.append('}');
        return sb.toString();
    }
}
