package com.ef.emergency.service;

import com.ef.emergency.dto.Location;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;

public abstract class ValidationTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    protected Validator validator;

    @BeforeEach
    public void init(){
        validator = factory.getValidator();
    }

    protected Location getValidTestLocation() {
        return getTestLocation(0,0);
    }

    protected Location getTestLocation(double latitude, double longitude) {
        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return location;
    }
}
