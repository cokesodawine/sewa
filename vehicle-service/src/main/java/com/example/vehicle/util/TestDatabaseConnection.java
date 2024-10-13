package com.example.vehicle.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TestDatabaseConnection {

    // No args constructor
    public TestDatabaseConnection () {}
    
    // Method to check on database connection
    public void checkDatabaseConnection () {
        try {
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/vehicle-db");
            Connection conn = ds.getConnection();
            System.out.println("Successfully connected to MySQL database using JNDI!");
            conn.close();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }
}



//#APPLYING SINGLE INSTANCE SERVICE MANUALLY
//public class VehicleService {
//    // Static variable to hold the single instance
//    private static VehicleService instance;
//
//    // Private constructor to prevent instantiation
//    private VehicleService() {}
//
//    // Static method to provide access to the single instance
//    public static synchronized VehicleService getInstance() {
//        if (instance == null) {
//            instance = new VehicleService();
//        }
//        return instance;
//    }
//
//    public void checkDBConnectivity() {
//        try {
//            Context initContext = new InitialContext();
//            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/vehicle-db");
//            Connection conn = ds.getConnection();
//            System.out.println("Successfully connected to MySQL database using JNDI!");
//            conn.close();
//        } catch (NamingException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}