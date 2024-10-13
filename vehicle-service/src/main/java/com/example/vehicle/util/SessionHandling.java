package com.example.vehicle.util;

import com.example.vehicle.model.Vehicle;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

@SessionScoped
public class SessionHandling implements Serializable{

    private static final long serialVersionUID = 1L;

    private CacheManager cacheManager;
    private Cache<String, Vehicle> vehicleCache;
    
    // Constructor
    public SessionHandling () {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("vehicleCache", CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        String.class, Vehicle.class, ResourcePoolsBuilder.heap(10))) // Adjust heap size as necessary
                .build(true);
        
        vehicleCache = cacheManager.getCache("vehicleCache", String.class, Vehicle.class);
    }

    // Cache a vehicle
    public void cacheVehicle (String vehicle_plat, Vehicle vehicle) {
        vehicleCache.put(vehicle_plat, vehicle);
    }
    
    // Retrieve a cached vehicle
    public Vehicle getCachedVehicle (String vehicle_plat) {
        return vehicleCache.get(vehicle_plat);
    }

    // Print cached data (if needed)
    public void printCacheData(){
        System.out.println("Printing vehicle cache data...");

        // Iterate over the cache entries
        for (Cache.Entry<String, Vehicle> entry : vehicleCache) {
            String key = entry.getKey();
            Vehicle vehicle = entry.getValue();

            // Print key and vehicle details
            System.out.println("Key: " + key);
            System.out.println("Vehicle: " + vehicle);
        }
    }

    // Clear cache
    public void clearCache() {
        vehicleCache.clear();
    }

    // Clean up resources on session destruction
    public void destroy() {
        cacheManager.close();;
        System.out.println("Caching Resource Cleaned");
    }
}
