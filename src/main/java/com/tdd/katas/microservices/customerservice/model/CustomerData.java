package com.tdd.katas.microservices.customerservice.model;

public class CustomerData {
    private String customerId;
    private String name;
    private String surnames;

    public CustomerData(String customerId, String name, String surnames) {
        this.customerId = customerId;
        this.name = name;
        this.surnames = surnames;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    @Override
    public boolean equals(Object obj) {
        return
                obj != null
                && obj instanceof CustomerData
                && this.customerId != null
                && this.customerId.equals(((CustomerData) obj).getCustomerId());
    }
}
