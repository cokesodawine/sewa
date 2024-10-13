package com.example.vehicle.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import javax.annotation.Resource;

import com.example.vehicle.model.Vehicle;

public class VehicleRepositoryImpl implements VehicleRepository{
    
    @Resource (name = "jdbc/vehicle-db")
    private DataSource datasource;

    @Override
    public List<Vehicle> getInstantAvailVehicles(){
        
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "select top 10 * from vehicles where vehicle_avail = 1";

        try (Connection conn = datasource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    vehicles.add (
                        new Vehicle.Builder()
                            .vehicle_plat(rs.getString("vehicle_plat"))
                            .vehicle_avail(rs.getBoolean("vehicle_avail"))
                            .vehicle_brand(rs.getString("vehicle_brand"))
                            .vehicle_model(rs.getString("vehicle_model"))
                            .vehicle_type(rs.getString("vehicle_type"))
                            .vehicle_color(rs.getString("vehicle_color"))
                            .fee_prhr(rs.getFloat("fee_prhr"))
                            .build()
                    );
                }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getSearchAvailVehicles(String startDate, String endDate) {

        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "select v.* "
                    + "from vehicles v "
                    + "left join bookings b "
                    + "on v.vehicle_plat = b.vehicle_plat "
                    + "and b.start_date <= '" + startDate + "' "
                    + "and b.end_date >= '" + endDate + "' "
                    + "where b.vehicle_plat is null;";

        try (Connection conn = datasource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    vehicles.add (
                        new Vehicle.Builder()
                            .vehicle_plat(rs.getString("vehicle_plat"))
                            .vehicle_avail(rs.getBoolean("vehicle_avail"))
                            .vehicle_brand(rs.getString("vehicle_brand"))
                            .vehicle_model(rs.getString("vehicle_model"))
                            .vehicle_type(rs.getString("vehicle_type"))
                            .vehicle_color(rs.getString("vehicle_color"))
                            .fee_prhr(rs.getFloat("fee_prhr"))
                            .build()
                    );
                }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public Vehicle getVehicleByPlat( String vehicle_plat ){

        Vehicle vehicle = null;
        String sql = "select * from vehicles where vehicle_plat = '" + vehicle_plat + "'";

        try (Connection conn = datasource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    vehicle = new Vehicle.Builder()
                            .vehicle_plat(rs.getString("vehicle_plat"))
                            .vehicle_avail(rs.getBoolean("vehicle_avail"))
                            .vehicle_brand(rs.getString("vehicle_brand"))
                            .vehicle_model(rs.getString("vehicle_model"))
                            .vehicle_type(rs.getString("vehicle_type"))
                            .vehicle_color(rs.getString("vehicle_color"))
                            .fee_prhr(rs.getFloat("fee_prhr"))
                            .build();
                }
        }catch (SQLException e){
            e.printStackTrace();
        }
        
        if (vehicle == null){ System.out.println("Vehicle not Found in Database"); } 
        System.out.println( "Vehicle retrieved from database" ); 
        return vehicle; 
    }

}