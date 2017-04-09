package com.tdd.katas.microservices.customerservice.service;

import com.tdd.katas.microservices.customerservice.model.CustomerData;

public interface CustomerService {
    CustomerData getCustomerData(String customer_id);
}
