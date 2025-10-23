package org.mdanylko.uav.simulator;

import org.mdanylko.uav.simulator.generator.UAVDataSimulator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UavSimulatorApplication implements CommandLineRunner {
    
    UAVDataSimulator simulator;

    public UavSimulatorApplication(UAVDataSimulator simulator) {
        this.simulator = simulator;
    }

    public static void main(String[] args) {
        //SpringApplication.run(UavSimulatorApplication.class, args);
        SpringApplication app = new SpringApplication(UavSimulatorApplication.class);
        //Run as cli for now. Revert this changes to make it run as webapp.
        app.setWebApplicationType(org.springframework.boot.WebApplicationType.NONE);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Run UAV Data Simulator");
        simulator.generateTelemetry();
    }
}
