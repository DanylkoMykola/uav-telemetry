package org.mdanylko.uav.simulator.simulator.sensor;

import org.mdanylko.uav.simulator.sensor.Battery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BatterySimulatorImpl implements BatterySimulator {

    private static final Logger log = LoggerFactory.getLogger(BatterySimulatorImpl.class);
    private static final double NOMINAL_VOLTAGE = 22.2; // 6S LiPo (6 cells * 3.7V nominal)
    private static final double MAX_VOLTAGE = 25.2;     // 6S fully charged (4.2V * 3)
    private static final double MIN_VOLTAGE = 19.8;      // 6S empty (3.3V * 3)

    private double capacityAh = 8.0;  // e.g., 2.2 Ah
    private double currentA = 5.0;
    private double remainingCapacityAh = 0.0;
    private final Random random = new Random();
    private long lastUpdateTimeMs;

    private Battery battery;

    public BatterySimulatorImpl() {
        this.battery = new Battery(0.0, 0.0);
    }

    public BatterySimulatorImpl(Battery battery) {
        this.battery = battery;
    }

    @Override
    public void simulateBattery() {
        long now = System.currentTimeMillis();
        double deltaTimeHours = (now - lastUpdateTimeMs) / 3600000.0; // ms → hours
        lastUpdateTimeMs = now;

        // Consume energy based on current
        remainingCapacityAh -= currentA * deltaTimeHours;
        if (remainingCapacityAh < 0) remainingCapacityAh = 0;

        double percentage = (remainingCapacityAh / capacityAh) * 100.0;

        // Estimate voltage based on remaining charge (rough linear model)
        double voltage = MIN_VOLTAGE + (MAX_VOLTAGE - MIN_VOLTAGE) * (percentage / 100.0);

        // Add some small random noise for realism
        voltage += (random.nextDouble() - 0.5) * 0.05;

        if (voltage < MIN_VOLTAGE) voltage = MIN_VOLTAGE;
        if (voltage > MAX_VOLTAGE) voltage = MAX_VOLTAGE;

        log.debug("Battery voltage: {:.2f} V | Current: {:.2f} A | Remaining: {:.1f}%",
                voltage, currentA, percentage);

        this.battery = new Battery(voltage, currentA);
    }

    public boolean isEmpty() {
        return remainingCapacityAh <= 0;
    }

    public void recharge() {
        remainingCapacityAh = capacityAh;
        log.info("Battery fully charged");
    }

    @Override
    public Battery getBattery() {
        return battery;
    }

    @Override
    public void setBattery(Battery battery) {
        this.battery = battery;
    }
}
