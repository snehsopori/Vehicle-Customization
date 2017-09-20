package com.cdk.dmg.vehicle.customization.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transaction_detail")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;

    private int cid;

    private int bill;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tid")
    private Set<Incrementor> incrementorHashSet = new HashSet<>();

    public Transaction() {
    }

    public Transaction(int bill, Set<Incrementor> incrementorHashSet) {
        this.incrementorHashSet = incrementorHashSet;
        this.bill = bill;
    }

    public Transaction(int cid, int bill, Set<Incrementor> incrementorHashSet) {
        this.bill = bill;
        this.cid = cid;
        this.incrementorHashSet = incrementorHashSet;
    }

    public Transaction(int tid, int cid, int bill, Set<Incrementor> incrementorHashSet) {
        this.tid = tid;
        this.bill = bill;
        this.cid = cid;
        this.incrementorHashSet = incrementorHashSet;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Set<Incrementor> getIncrementorHashSet() {
        return incrementorHashSet;
    }

    public void setIncrementorHashSet(Set<Incrementor> incrementorHashSet) {
        this.incrementorHashSet = incrementorHashSet;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "tid=" + tid +
                ", cid=" + cid +
                ", bill=" + bill +
                ", incrementorHashSet=" + incrementorHashSet +
                '}';
    }
}
