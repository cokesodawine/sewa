package com.example.vehicle.controller;

import com.example.vehicle.model.Vehicle;
import com.example.vehicle.service.VehicleService;
import com.example.vehicle.util.SessionHandling;
import com.example.vehicle.util.TrashToJSON;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// Get full details of a vehicle based on prompted vehicle_plat
@WebServlet ( name = "specific-vehicle", urlPatterns = "/vehicle/specific/*" )
public class SpecificVehicleController extends HttpServlet {
    
    @Inject
    private VehicleService vehicleService;
    @Inject 
    private SessionHandling sessionHandling;

    // No Args Constructor
    public SpecificVehicleController() {}

    // Get Method
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get url parameter
        String reqParam = request.getPathInfo();

        // Bad url handling
        if ( reqParam == null || reqParam.split("/").length<2 ) {
            response.getWriter().write("Invalid path parameters from specific controller");
            return;
        }

        // Retrieve vehicle_plat
        String vehicle_plat = reqParam.split("/")[1];

        // Try to retrieve vehicle from cache
        Vehicle vehicle = sessionHandling.getCachedVehicle(vehicle_plat);
        if (vehicle != null) {
            System.out.println("Vehicle retrieved from cache");
            TrashToJSON.printJSON("Vehicle [" +  vehicle_plat + "] details :", vehicle);
            return; 
        }

        // If fail to retrieve from cache
        vehicle = vehicleService.getSpecificVehicleDetails(vehicle_plat);

        // Print JSON
        TrashToJSON.printJSON("Vehicle [" +  vehicle_plat + "] details :", vehicle);
        return; 
    }


}
