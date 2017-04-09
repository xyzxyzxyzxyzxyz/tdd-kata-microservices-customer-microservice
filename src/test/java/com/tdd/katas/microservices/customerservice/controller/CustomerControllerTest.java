package com.tdd.katas.microservices.customerservice.controller;

import com.tdd.katas.microservices.customerservice.model.CustomerData;
import com.tdd.katas.microservices.customerservice.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerController.class)
@WithMockUser
public class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void Can_create_instance() {
        assertNotNull("Should be able to create instance", customerController);
    }

    @Test
    public void It_returns_valid_data_for_a_valid_input() throws Exception {

        final String CUSTOMER_ID = "1";

        CustomerData expectedCustomerData = new CustomerData(CUSTOMER_ID,"Sergio", "Osuna Medina");

        given(customerService.getCustomerData(CUSTOMER_ID)).willReturn(expectedCustomerData);

        this.mvc.perform(
                get("/customers/" + CUSTOMER_ID)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.customerId", is(expectedCustomerData.getCustomerId())))
                .andExpect(jsonPath("$.name", is(expectedCustomerData.getName())))
                .andExpect(jsonPath("$.surnames", is(expectedCustomerData.getSurnames())));

        verify(customerService).getCustomerData(CUSTOMER_ID);

    }

    @Test
    public void It_returns_404_if_the_customerId_does_not_exist() throws Exception {

        given(customerService.getCustomerData(any())).willReturn(null);

        this.mvc
                .perform(
                        get("/customers/" + "PEPITO")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());

        verify(customerService).getCustomerData(any());

    }

    @Test
    public void It_returns_500_if_the_service_throws_an_error() throws Exception {

        final String CUSTOMER_ID = "1";

        given(customerService.getCustomerData(any())).willThrow(
                new IllegalStateException("database is not ready"));

        this.mvc
                .perform(
                        get("/customers/" + CUSTOMER_ID)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isInternalServerError());

        verify(customerService).getCustomerData(any());

    }

}
