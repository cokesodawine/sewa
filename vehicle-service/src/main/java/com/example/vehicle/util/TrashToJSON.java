package com.example.vehicle.util;

import com.example.vehicle.model.Vehicle;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

// Magically turn 💩 into JSON
public class TrashToJSON {
    
    // No Args
    public TrashToJSON() {}

    // Print JSON
    public static <T> void printJSON (String context, T trash){
        System.out.println("\n" + context + "\n");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(trash)); 
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("no JSON generated");
        }
    }
}
