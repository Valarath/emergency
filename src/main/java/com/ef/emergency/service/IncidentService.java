package com.ef.emergency.service;

import com.ef.emergency.dto.*;
import com.ef.emergency.elasticsearch.IncidentElasticSearchService;
import com.ef.emergency.persistence.IncidentPersistenceRepository;
import com.ef.emergency.persistence.IncidentPersistenceEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IncidentService {

    private final IncidentPersistenceRepository incidentPersistenceRepository;
    private final IncidentElasticSearchService incidentElasticSearchService;
    private final IncidentMapper incidentMapper;

    public IncidentService(IncidentPersistenceRepository incidentPersistenceRepository, IncidentElasticSearchService incidentElasticSearchService, IncidentMapper incidentMapper) {
        this.incidentPersistenceRepository = incidentPersistenceRepository;
        this.incidentElasticSearchService = incidentElasticSearchService;
        this.incidentMapper = incidentMapper;
    }

    @Transactional
    public Incident log(Location location, IncidentType incidentType, SeverityLevel severity) {
        Incident incident = incidentMapper.toIncident(location, incidentType, severity);
        persistInDatabase(incident);
        incidentElasticSearchService.log(incident);
        return incident;
    }

    private void persistInDatabase(Incident incident) {
        IncidentPersistenceEntity incidentPersistenceEntity = incidentMapper.toIncidentPersistenceEntity(incident);
        incidentPersistenceRepository.save(incidentPersistenceEntity);
    }

    public List<Incident> search(Page page, SearchFilter filter) {
        return incidentElasticSearchService.search(page, filter);
    }


}
