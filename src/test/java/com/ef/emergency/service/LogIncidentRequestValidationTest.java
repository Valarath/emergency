package com.ef.emergency.service;

import com.ef.emergency.api.incident.LogIncidentRequest;
import com.ef.emergency.dto.IncidentType;
import com.ef.emergency.dto.Location;
import com.ef.emergency.dto.SeverityLevel;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LogIncidentRequestValidationTest extends ValidationTest{

    @Test
    public void testThatValidRequestPass() {
        Location location = getValidTestLocation();
        LogIncidentRequest request = new LogIncidentRequest(IncidentType.FIRE, location, SeverityLevel.HIGH);
        Set<ConstraintViolation<LogIncidentRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void testValidationOnLogRequestNecessaryValues() {
        LogIncidentRequest request = new LogIncidentRequest(null, null, null);
        Set<ConstraintViolation<LogIncidentRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(),3);
    }
}
