package com.example.vehicle.service;

import com.example.vehicle.repository.VehicleRepository;
import com.example.vehicle.model.Vehicle;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class VehicleService {

    @Inject
    private VehicleRepository vehicleRepository;

    // No Args Constructor - to confirm ApplicationScoped service
    public VehicleService () {}

    // Method executes right after instance creation
    @PostConstruct
    public void init(){}

    // Instant vehicle catalogue
    public List<Vehicle> getInstantVehiclesCatalogue() {
        return vehicleRepository.getInstantAvailVehicles();
    }

    // Search vehicle catalogue
    public List <Vehicle> getSearchVehicleCatalogue( String startDate, String endDate ){
        return vehicleRepository.getSearchAvailVehicles(startDate, endDate);
    }

    // Get specific vehicle by vehicle_plat
    public Vehicle getSpecificVehicleDetails( String vehicle_plat ){
        return vehicleRepository.getVehicleByPlat(vehicle_plat);
    }

    // Method executes right before instance termination
    @PreDestroy
    public void destroy() {}

    public synchronized void serviceSimulation (){
        System.out.println("Countdown starting...");
        
        for (int i = 5; i > 0; i--) {
            System.out.println("Countdown: " + i + " seconds remaining");
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Countdown complete!");
    }
}