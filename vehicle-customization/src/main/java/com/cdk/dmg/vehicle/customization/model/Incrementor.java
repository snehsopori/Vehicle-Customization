package com.cdk.dmg.vehicle.customization.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "incrementor")
public class Incrementor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int increment;

    private int aid;

    public Incrementor() {
    }

    public Incrementor(int increment, int aid) {
        this.increment = increment;
        this.aid = aid;
    }

    public Incrementor(int aid) {
        this.aid = aid;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "Incrementor{" +
                "increment=" + increment +
                ", aid=" + aid +
                '}';
    }
}
