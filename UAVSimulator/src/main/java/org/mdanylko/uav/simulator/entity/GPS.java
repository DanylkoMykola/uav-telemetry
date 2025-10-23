package org.mdanylko.uav.simulator.entity;

public class GPS {

    private double lat;
    private double lon;
    private double alt;

    public GPS(double lat, double lon, double alt) {
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
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
