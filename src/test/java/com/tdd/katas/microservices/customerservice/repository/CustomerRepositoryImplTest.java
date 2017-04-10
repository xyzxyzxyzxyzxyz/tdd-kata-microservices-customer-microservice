package com.tdd.katas.microservices.customerservice.repository;

import com.tdd.katas.microservices.customerservice.model.CustomerData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = CustomerRepositoryImpl.class)
@RunWith(SpringRunner.class)
public class CustomerRepositoryImplTest
{

    @Autowired
    private CustomerRepositoryImpl customerRepository;

    @Before
    public void setUp() {
        // Clear repository between tests
        customerRepository.deleteAllCustomerData();
    }

    @Test
    public void The_repository_returns_valid_output_for_valid_input() throws Exception {
        String CUSTOMER_ID = "X";

        CustomerData expectedCustomerData = new CustomerData(CUSTOMER_ID,"Sergio", "Osuna Medina");

        customerRepository.createCustomerData(CUSTOMER_ID, expectedCustomerData);

        CustomerData actualCustomerData =  customerRepository.getCustomerData(CUSTOMER_ID);

        assertEquals("Should return the stored customer data", expectedCustomerData, actualCustomerData);
    }

    @Test
    public void The_repository_returns_null_for_invalid_input() throws Exception {
        CustomerData existingCustomerData = new CustomerData("1","Sergio", "Osuna Medina");
        customerRepository.createCustomerData(existingCustomerData.getCustomerId(), existingCustomerData);

        CustomerData actualCustomerData =  customerRepository.getCustomerData("2");

        assertNull("The customer data should not exist in the database", actualCustomerData);
    }

    @Test
    public void The_repository_accepts_creating_a_non_existing_vin() throws Exception {
        final String VIN = "X";

        CustomerData expectedCustomerData = new CustomerData("CUSTOMERID_X", "NAME_X", "SURNAMES_X");

        customerRepository.createCustomerData(VIN, expectedCustomerData);

        CustomerData actualCustomerData =  customerRepository.getCustomerData(VIN);

        assertEquals("The customer data should exist in the repository after creation", expectedCustomerData, actualCustomerData);
    }

    @Test
    public void The_repository_does_not_accept_creating_an_already_existing_vin() throws Exception {
        final String VIN = "X";

        CustomerData expectedCustomerData = new CustomerData("CUSTOMERID_X", "NAME_X", "SURNAMES_X");

        customerRepository.createCustomerData(VIN, expectedCustomerData);

        CustomerData actualCustomerData =  customerRepository.getCustomerData(VIN);

        assertEquals("The customer data should exist in the repository after creation", expectedCustomerData, actualCustomerData);

        try {
            customerRepository.createCustomerData(VIN, expectedCustomerData);
            fail("Should not have accepted the creation of an already existing VIN");
        }
        catch (IllegalStateException e) {
            // Ok
        }

    }

    @Test
    public void The_repository_allows_deleting_all_the_data() throws Exception {
        final String VIN_X = "VIN_X";
        final String VIN_Y = "VIN_Y";
        
        customerRepository.createCustomerData(VIN_X, new CustomerData("CUSTOMERID_X", "NAME_X", "SURNAMES_X"));
        customerRepository.createCustomerData(VIN_Y, new CustomerData("PLATENUMBER_Y", "MODEL_Y", "COLOR_Y"));

        assertNotNull("The customer data should exist after creation", customerRepository.getCustomerData(VIN_X));
        assertNotNull("The customer data should exist after creation", customerRepository.getCustomerData(VIN_Y));

        customerRepository.deleteAllCustomerData();

        assertNull("The customer data should not exist after clearing the repository", customerRepository.getCustomerData(VIN_X));
        assertNull("The customer data should not exist after clearing the repository", customerRepository.getCustomerData(VIN_Y));
    }

}
