package com.ef.emergency.service;

import com.ef.emergency.api.incident.SearchIncidentRequest;
import com.ef.emergency.dto.Page;
import com.ef.emergency.dto.SearchFilter;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SearchIncidentRequestValidationTest extends ValidationTest {

    @Test
    public void testThatValidPagePass(){
        Page page = new Page();
        Set<ConstraintViolation<Page>> violations = validator.validate(page);
        assertTrue(violations.isEmpty());
        assertNotNull(violations);
    }

    @Test
    public void testPageMinValues(){
        Page page = new Page();
        page.setPageNumber(-1);
        page.setPageSize(-10);
        Set<ConstraintViolation<Page>> violations = validator.validate(page);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(),2);
    }

    @Test
    public void testThatValidRequestPass(){
        SearchIncidentRequest searchIncidentRequest = new SearchIncidentRequest(null, getValidSearchFilter());
        Set<ConstraintViolation<SearchIncidentRequest>> violations = validator.validate(searchIncidentRequest);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testThatLocationCannotBeNull(){
        SearchFilter filter = getValidSearchFilter();
        filter.setLocation(null);
        Set<ConstraintViolation<SearchFilter>> violations = validator.validate(filter);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(),1);
    }

    @Test
    public void testMinDistanceFilterValue(){
        SearchFilter filter = getValidSearchFilter();
        filter.setDistance(0);
        Set<ConstraintViolation<SearchFilter>> violations = validator.validate(filter);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(),1);
    }

    @Test
    public void testThatFromCannotBeInFuture(){
        Instant futureTime = Instant.now().plus(1, ChronoUnit.MINUTES);
        SearchFilter filter = getValidSearchFilter();
        filter.setFrom(futureTime);
        filter.setTo(futureTime);
        Set<ConstraintViolation<SearchFilter>> violations = validator.validate(filter);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(),1);
    }

    @Test
    public void testThatFromCannotBeAfterTo(){
        Instant pastTime = Instant.now().minus(1, ChronoUnit.MINUTES);
        SearchFilter filter = getValidSearchFilter();
        filter.setTo(pastTime);
        Set<ConstraintViolation<SearchFilter>> violations = validator.validate(filter);
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(),1);
    }

    private SearchFilter getValidSearchFilter(){
        SearchFilter filter = new SearchFilter();
        filter.setLocation(getValidTestLocation());
        return filter;
    }

}
