package com.example.vehicle.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class Vehicle {
    
    @JsonProperty
    private String vehicle_plat;
    @JsonProperty
    private Boolean vehicle_avail;
    @JsonProperty
    private String vehicle_brand;
    @JsonProperty
    private String vehicle_model;
    @JsonProperty
    private String vehicle_type;
    @JsonProperty
    private String vehicle_color;
    @JsonProperty
    private float fee_prhr;

    // NoArgsConstructor
    public Vehicle(){}

    // AllArgsContructor
    public Vehicle(String vehicle_plat, Boolean vehicle_avail, String vehicle_brand, 
            String vehicle_model, String vehicle_type, String vehicle_color, float fee_prhr){
        this.vehicle_plat = vehicle_plat;
        this.vehicle_avail = vehicle_avail;
        this.vehicle_brand = vehicle_brand;
        this.vehicle_model = vehicle_model;
        this.vehicle_type = vehicle_type;
        this.vehicle_color = vehicle_color;
        this.fee_prhr = fee_prhr;
    }

    // Builder constructor
    private Vehicle (Builder builder){
        this.vehicle_plat = builder.vehicle_plat;
        this.vehicle_avail = builder.vehicle_avail;
        this.vehicle_brand = builder.vehicle_brand;
        this.vehicle_model = builder.vehicle_model;
        this.vehicle_type = builder.vehicle_type;
        this.vehicle_color = builder.vehicle_color;
        this.fee_prhr = builder.fee_prhr;
    }

    // Builder (might apply DI later :/)
    public static class Builder {
        private String vehicle_plat;
        private Boolean vehicle_avail;
        private String vehicle_brand;
        private String vehicle_model;
        private String vehicle_type;
        private String vehicle_color;
        private float fee_prhr;

        public Builder vehicle_plat (String vehicle_plat){
            this.vehicle_plat = vehicle_plat;
            return this;
        }
        public Builder vehicle_avail (Boolean vehicle_avail){
            this.vehicle_avail = vehicle_avail;
            return this;
        }
        public Builder vehicle_brand (String vehicle_brand){
            this.vehicle_brand = vehicle_brand;
            return this;
        }
        public Builder vehicle_model (String vehicle_model){
            this.vehicle_model = vehicle_model;
            return this;
        }
        public Builder vehicle_type (String vehicle_type){
            this.vehicle_type = vehicle_type;
            return this;
        }
        public Builder vehicle_color (String vehicle_color){
            this.vehicle_color = vehicle_color;
            return this;
        }
        public Builder fee_prhr (float fee_prhr){
            this.fee_prhr = fee_prhr;
            return this;
        }
        public Vehicle build(){
            return new Vehicle(this);
        }
    }
}
