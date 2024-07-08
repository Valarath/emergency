package com.ef.emergency.service;

import com.ef.emergency.dto.*;
import com.ef.emergency.elasticsearch.IncidentElasticSearchService;
import com.ef.emergency.persistence.IncidentPersistenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncidentServiceTest {

    private static final Location TEST_LOCATION = new Location();
    private static final IncidentType TEST_INCIDENT_TYPE = IncidentType.FIRE;
    private static final SeverityLevel TEST_SEVERITY_LEVEL = SeverityLevel.MEDIUM;
    private static final Page TEST_PAGE = new Page();
    private static final SearchFilter TEST_FILTER = new SearchFilter();
    private static final String TEST_ID = "test";

    @Mock
    private IncidentPersistenceRepository incidentPersistenceRepository;
    @Mock
    private IncidentElasticSearchService incidentElasticSearchService;
    @Spy
    private IncidentMapper incidentMapper;

    @InjectMocks
    private IncidentService incidentService;

    @Test
    void testThatOnValidInputIncidentIsLogged() {
        Incident incident = incidentService.log(TEST_LOCATION, TEST_INCIDENT_TYPE, TEST_SEVERITY_LEVEL);
        validateLogIncident(incident);
    }

    @Test
    void testThatIfIncidentExistsSearchReturnsThem() {
        List<Incident> expectedIncidents = List.of(getTestIncident(),getTestIncident());
        when(incidentElasticSearchService.search(TEST_PAGE, TEST_FILTER)).thenReturn(expectedIncidents);
        List<Incident> searchResult = incidentService.search(TEST_PAGE, TEST_FILTER);
        validateThatSearchAndExpectedResultsAreEquals(searchResult, expectedIncidents);
    }

    @Test
    void testThatSearchCanReturnEmptyList() {
        List<Incident> expectedIncidents = List.of();
        when(incidentElasticSearchService.search(TEST_PAGE, TEST_FILTER)).thenReturn(expectedIncidents);
        List<Incident> searchResult = incidentService.search(TEST_PAGE, TEST_FILTER);
        validateThatSearchResultIsEmpty(searchResult);
    }

    private void validateThatSearchResultIsEmpty(List<Incident> searchResult) {
        assertNotNull(searchResult);
        assertTrue(searchResult.isEmpty());
    }

    private void validateThatSearchAndExpectedResultsAreEquals(List<Incident> searchResult, List<Incident> expectedIncidents) {
        assertNotNull(searchResult);
        assertFalse(searchResult.isEmpty());
        assertIterableEquals(expectedIncidents, searchResult);
    }

    private Incident getTestIncident() {
        return new Incident(TEST_ID, TEST_INCIDENT_TYPE, TEST_LOCATION, Instant.now(), TEST_SEVERITY_LEVEL);
    }

    private void validateLogIncident(Incident incident) {
        assertNotNull(incident);
        assertEquals(TEST_LOCATION, incident.location());
        assertEquals(TEST_INCIDENT_TYPE, incident.type());
        assertEquals(TEST_SEVERITY_LEVEL, incident.severityLevel());
        assertNotNull(incident.id());
        assertNotNull(incident.timestamp());
    }

}