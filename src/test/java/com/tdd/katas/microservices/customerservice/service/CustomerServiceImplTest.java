package com.tdd.katas.microservices.customerservice.service;

import com.tdd.katas.microservices.customerservice.model.CustomerData;
import com.tdd.katas.microservices.customerservice.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CustomerServiceImpl.class)
public class CustomerServiceImplTest {
    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void The_service_delegates_the_calls_to_the_repository() {
        String CUSTOMER_ID = "X";

        CustomerData expectedCustomerData = new CustomerData(CUSTOMER_ID,"Sergio", "Osuna Medina");

        given(customerRepository.getCustomerData(CUSTOMER_ID)).willReturn(expectedCustomerData);

        CustomerData actualCustomerData = customerService.getCustomerData(CUSTOMER_ID);

        // The service must delegate the call to the repository with the same input
        verify(customerRepository).getCustomerData(CUSTOMER_ID);

        assertEquals("The service should return the CustomerData as provided by the repository", expectedCustomerData, actualCustomerData);
    }

    @Test
    public void The_service_propagates_the_errors_from_the_repository() {

        given(customerRepository.getCustomerData(any())).willThrow(new IllegalStateException("database not ready"));

        try {
            CustomerData actualCustomerData = customerRepository.getCustomerData(any());
            fail("Should have thrown an exception");
        } catch (IllegalStateException e) {
            // The error has been propagated by the service
        }

        // The service must delegate the call to the repository with the same input
        verify(customerRepository).getCustomerData(any());


    }

}
