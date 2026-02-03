package org.mdanylko.uav.simulator;

import org.mdanylko.uav.simulator.simulator.UAVDataSimulator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UavSimulatorApplication {

    public static void main(String[] args) {
        //SpringApplication.run(UavSimulatorApplication.class, args);
        //SpringApplication app = new SpringApplication(UavSimulatorApplication.class);
        //Run as cli for now. Revert this changes to make it run as webapp.
        //app.setWebApplicationType(org.springframework.boot.WebApplicationType.NONE);
        //app.run(args);

        ApplicationContext context = SpringApplication.run(UavSimulatorApplication.class, args);
        UAVDataSimulator simulator = context.getBean(UAVDataSimulator.class);
        System.out.println("Run UAV Data Simulator");
        simulator.generateTelemetry();
    }
}
