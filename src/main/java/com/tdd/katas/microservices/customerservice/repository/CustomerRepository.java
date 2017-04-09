package com.tdd.katas.microservices.customerservice.repository;


import com.tdd.katas.microservices.customerservice.model.CustomerData;

public interface CustomerRepository {
    CustomerData getCustomerData(String customer_id);
}
