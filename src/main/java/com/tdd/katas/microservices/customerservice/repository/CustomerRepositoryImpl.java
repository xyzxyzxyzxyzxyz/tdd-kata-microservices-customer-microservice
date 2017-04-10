package com.tdd.katas.microservices.customerservice.repository;

import com.tdd.katas.microservices.customerservice.model.CustomerData;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private Map<String,CustomerData> map = new HashMap<>();

    @Override
    public CustomerData getCustomerData(String customer_id) {
        return map.get(customer_id);
    }

    @Override
    public void createCustomerData(String vin, CustomerData customerData) throws IllegalStateException {
        if (map.containsKey(vin)) {
            throw new IllegalStateException("Repository already contains a CustomerData with VIN: ["+vin+"]");
        }
        map.put(vin, customerData);
    }

    @Override
    public void deleteAllCustomerData() {
        map.clear();
    }

}
