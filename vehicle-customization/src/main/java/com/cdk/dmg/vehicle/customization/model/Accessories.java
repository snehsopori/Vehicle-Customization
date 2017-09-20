package com.cdk.dmg.vehicle.customization.model;

import javax.persistence.*;

@Entity
@Table(name = "accessories_detail")
public class Accessories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id")
    private int accId;

    @Column(name = "accessory_name")
    private String accessoryName;

    @Column(name = "accessory_price")
    private double accessoryPrice;

    private String image;

    public Accessories() {
    }

    public Accessories(String accessoryName, double accessoryPrice, String image) {
        this.accessoryName = accessoryName;
        this.accessoryPrice = accessoryPrice;
        this.image = image;
    }

    public Accessories(int accId, String accessoryName, double accessoryPrice, String image) {
        this.accId = accId;
        this.accessoryName = accessoryName;
        this.accessoryPrice = accessoryPrice;
        this.image = image;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    public double getAccessoryPrice() {
        return accessoryPrice;
    }

    public void setAccessoryPrice(double accessoryPrice) {
        this.accessoryPrice = accessoryPrice;
    }

    @Override
    public String toString() {
        return "Accessories{" +
                "accId=" + accId +
                ", accessoryName='" + accessoryName + '\'' +
                ", accessoryPrice=" + accessoryPrice +
                '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
