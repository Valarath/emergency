package com.ef.emergency.service;

import com.ef.emergency.dto.Incident;
import com.ef.emergency.dto.IncidentType;
import com.ef.emergency.dto.Location;
import com.ef.emergency.dto.SeverityLevel;
import com.ef.emergency.elasticsearch.IncidentElasticsearchEntity;
import com.ef.emergency.persistence.IncidentPersistenceEntity;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class IncidentMapper {

    public Incident toIncident(Location location, IncidentType incidentType, SeverityLevel severity) {
        return new Incident(getId(),
                incidentType,
                location,
                getTimestamp(),
                severity);
    }

    public Incident toIncident(IncidentElasticsearchEntity incidentElasticsearchEntity) {
        return new Incident(incidentElasticsearchEntity.getId(),
                incidentElasticsearchEntity.getType(),
                toLocation(incidentElasticsearchEntity.getLocation()),
                incidentElasticsearchEntity.getTimestamp(),
                incidentElasticsearchEntity.getSeverityLevel());
    }

    public IncidentPersistenceEntity toIncidentPersistenceEntity(Incident incident) {
        IncidentPersistenceEntity incidentPersistenceEntity = new IncidentPersistenceEntity();
        incidentPersistenceEntity.setLocation(incident.location());
        incidentPersistenceEntity.setType(incident.type());
        incidentPersistenceEntity.setSeverityLevel(incident.severityLevel());
        incidentPersistenceEntity.setTimestamp(incident.timestamp());
        incidentPersistenceEntity.setId(incident.id());
        return incidentPersistenceEntity;
    }

    public IncidentElasticsearchEntity toIncidentElasticsearchEntity(Incident incident) {
        IncidentElasticsearchEntity incidentElasticsearchEntity = new IncidentElasticsearchEntity();
        incidentElasticsearchEntity.setLocation(toGeoPoint(incident.location()));
        incidentElasticsearchEntity.setType(incident.type());
        incidentElasticsearchEntity.setSeverityLevel(incident.severityLevel());
        incidentElasticsearchEntity.setTimestamp(incident.timestamp());
        incidentElasticsearchEntity.setId(incident.id());
        return incidentElasticsearchEntity;
    }

    public GeoPoint toGeoPoint(Location location) {
        return new GeoPoint(location.getLatitude(),location.getLongitude());
    }

    private Location toLocation(GeoPoint geoPoint) {
        Location location = new Location();
        location.setLatitude(geoPoint.getLat());
        location.setLongitude(geoPoint.getLon());
        return location;
    }

    private Instant getTimestamp() {
        return Instant.now();
    }

    private String getId() {
        return UUID.randomUUID().toString();
    }
}
