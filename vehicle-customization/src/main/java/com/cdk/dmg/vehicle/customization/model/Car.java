package com.cdk.dmg.vehicle.customization.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "car_detail")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vin;
    private String make;
    private String model;
    private int year;
    private String ctrim;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "aid")
    private Set<Accessories> accessories = new HashSet<>();

    public Car() {
    }

    public Car(String make, String model, int year, String trim, Set<Accessories> accessories) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.ctrim = trim;
        this.accessories = accessories;
    }

    public Car(int vin, String make, String model, int year, String trim, Set<Accessories> accessories) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.ctrim = trim;
        this.accessories = accessories;
    }

    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTrim() {
        return ctrim;
    }

    public void setTrim(String trim) {
        this.ctrim = trim;
    }

    public Set<Accessories> getAccessories() {
        return accessories;
    }

    public void setAccessories(Set<Accessories> accessories) {
        this.accessories = accessories;
    }

    @Override
    public String toString() {
        return "Car{" +
                "vin=" + vin +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", trim='" + ctrim + '\'' +
                ", accessories=" + accessories +
                '}';
    }
}
