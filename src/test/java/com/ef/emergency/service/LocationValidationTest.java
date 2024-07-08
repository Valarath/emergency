package com.ef.emergency.service;

import com.ef.emergency.dto.Location;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationValidationTest extends ValidationTest {

    @Test
    public void testThatValidLocationValidationsPass(){
        Location location = getValidTestLocation();
        Set<ConstraintViolation<Location>> violations = validator.validate(location);
        assertTrue(violations.isEmpty());
        assertNotNull(violations);
    }

    @Test
    public void testLocationMaxValues(){
        Location location = getTestLocation(91, 181);
        Set<ConstraintViolation<Location>> violations = validator.validate(location);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(),2);
    }

    @Test
    public void testLocationMinValues(){
        Location location = getTestLocation(-91, -181);
        Set<ConstraintViolation<Location>> violations = validator.validate(location);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(),2);
    }
}
