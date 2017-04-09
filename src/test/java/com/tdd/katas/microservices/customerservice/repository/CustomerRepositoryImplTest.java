package com.tdd.katas.microservices.customerservice.repository;

import com.tdd.katas.microservices.customerservice.model.CustomerData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ContextConfiguration(classes = CustomerRepositoryImpl.class)
@RunWith(SpringRunner.class)
public class CustomerRepositoryImplTest
{

    @Autowired
    private CustomerRepositoryImpl customerRepository;

    @Test
    public void The_repository_returns_valid_output_for_valid_input() throws Exception {
        String CUSTOMER_ID = "X";

        CustomerData expectedCustomerData = new CustomerData(CUSTOMER_ID,"Sergio", "Osuna Medina");

        customerRepository.store(CUSTOMER_ID, expectedCustomerData);

        CustomerData actualCustomerData =  customerRepository.getCustomerData(CUSTOMER_ID);

        assertEquals("Should return the stored customer data", expectedCustomerData, actualCustomerData);
    }

    @Test
    public void The_repository_returns_null_for_invalid_input() throws Exception {
        CustomerData existingCustomerData = new CustomerData("1","Sergio", "Osuna Medina");
        customerRepository.store(existingCustomerData.getCustomerId(), existingCustomerData);

        CustomerData actualCustomerData =  customerRepository.getCustomerData("2");

        assertNull("The customer data should not exist in the database", actualCustomerData);
    }

}
