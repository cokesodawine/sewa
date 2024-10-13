package com.example.vehicle.controller;

import com.example.vehicle.service.VehicleService;
import com.example.vehicle.util.SessionHandling;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.util.TrashToJSON;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// For users who want to check on avalaible vehicle from 20xx-xx-xx until 20xx-xx-xx
@WebServlet ( name = "search-vehicle", urlPatterns = "/vehicles/search/*" )
public class SearchVehicleController extends HttpServlet{
    
    @Inject
    private VehicleService vehicleService;
    @Inject
    private SessionHandling sessionHandling;

    // No args constructor
    public SearchVehicleController() {}

    // Get method
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Get url parameter
        String reqParam = request.getPathInfo();

        // Bad url handling
        if ( reqParam == null || reqParam.split("/").length<3 ) {
            response.getWriter().write("Invalid path parameters from search controller.");
            return;
        }

        // Separate the start-end date by user (YYYY-MM-DD)
        String[] paramArr = reqParam.split("/");
        String startDate = paramArr[1];
        String endDate = paramArr[2];

        // Service method call
        List<Vehicle> vehicles = vehicleService.getSearchVehicleCatalogue(startDate, endDate);

        // Cache the vehicles
        for (Vehicle vehicle : vehicles) {
            sessionHandling.cacheVehicle(vehicle.getVehicle_plat(), vehicle);
        }

        // Print in JSON form
        TrashToJSON.printJSON("Available vehicles on " + startDate + " until " + endDate + " :", vehicles);
    }


}
