package com.tdd.katas.microservices.customerservice.config;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ActiveProfiles(Profiles.PROFILE_INTEGRATION_TEST)
@ContextConfiguration(classes = DataFileLoaderExecutor.class)
public class DataFileLoaderExecutorTest {

    @MockBean
    private DataFileLoader dataFileLoader;
    @Autowired
    private DataFileLoaderExecutor dataFileLoaderExecutor;

    @Test
    public void Loader_is_executed_on_application_startup() {
        verify(dataFileLoader).loadData();
    }

}
