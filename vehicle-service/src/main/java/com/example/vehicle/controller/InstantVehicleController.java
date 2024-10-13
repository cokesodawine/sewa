package com.example.vehicle.controller;

import com.example.vehicle.service.VehicleService;
import com.example.vehicle.util.SessionHandling;
import com.example.vehicle.util.TrashToJSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.vehicle.model.Vehicle;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// For users who want to check on currently available vehicles
@WebServlet ( name = "instant-vehicle", urlPatterns = "/vehicles/instant")
public class InstantVehicleController extends HttpServlet {

    // to resolve java.lang.NoSuchMethodException: com.example.vehicle.controller.VehicleController.<init>() 
    // means that the application is looking for a no-argument constructor : 
    // this can be done by declaring no arg constructor and remove 'final' of vehicleService so that Tomcat can pre-initialze this controller using no arg constructor
    // initially, vehicleService ws declared as Final to avoid service reassignment (which might be a good practice...i guess...)
    @Inject
    private VehicleService vehicleService;
    @Inject
    private SessionHandling sessionHandling;
    
    // No-argument constructor required by Tomcat)
    public InstantVehicleController() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Service method call
        List<Vehicle> vehicles = vehicleService.getInstantVehiclesCatalogue();

        // Cache the vehicles
        for (Vehicle vehicle : vehicles) {
            sessionHandling.cacheVehicle(vehicle.getVehicle_plat(), vehicle);
        }

        // Print in JSON form
        TrashToJSON.printJSON("Available vehicles :", vehicles);

        // Set content type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Convert the vehicles list to JSON
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(vehicles);
        response.getWriter().write(json);

        // Inject into JSP doc
        //request.setAttribute("vehiclesList", vehicles);
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/VehiclesCatalogue.jsp");
        //dispatcher.forward(request, response);
    }
}