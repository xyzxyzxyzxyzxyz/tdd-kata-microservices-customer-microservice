package com.tdd.katas.microservices.customerservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * This class executes the {@link DataFileLoader} on application start.
 * It is only executed when the profile {@code Profiles.PROFILE_INTEGRATION_TEST} is active.
 *
 * TODO:
 * Find the way to do the execution hooking into the lifecycle of the SpringBoot
 * application.
 */
@Component
@Profile(Profiles.PROFILE_INTEGRATION_TEST)
public class DataFileLoaderExecutor {

    @Autowired
    private DataFileLoader dataFileLoader;

    @PostConstruct
    public void loadData() {
        dataFileLoader.loadData();
    }

}
