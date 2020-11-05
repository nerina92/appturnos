package com.apiturns.demo.entity;

public class Court {

    int court_id;
    String name;
    String address = "";
    String details = "";

    public Court() {
    }

    public int getCourt_id() {
        return court_id;
    }

    public void setCourt_id(int court_id) {
        this.court_id = court_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Court{" +
                "court_id=" + court_id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
