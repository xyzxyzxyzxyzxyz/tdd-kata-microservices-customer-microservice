package com.tdd.katas.microservices.customerservice.repository;

import com.tdd.katas.microservices.customerservice.model.CustomerData;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private Map<String,CustomerData> map = new HashMap<>();

    void store(String customer_id, CustomerData expectedCustomerData) {
        map.put(customer_id, expectedCustomerData);
    }

    @Override
    public CustomerData getCustomerData(String customer_id) {
        return map.get(customer_id);
    }

}
