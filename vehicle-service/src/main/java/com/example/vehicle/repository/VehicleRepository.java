package com.example.vehicle.repository;

import com.example.vehicle.model.Vehicle;

import java.util.List;

public interface VehicleRepository {
    List<Vehicle> getInstantAvailVehicles();
    List<Vehicle> getSearchAvailVehicles( String startDate, String endDate );
    Vehicle getVehicleByPlat( String vehicle_plat );
}
