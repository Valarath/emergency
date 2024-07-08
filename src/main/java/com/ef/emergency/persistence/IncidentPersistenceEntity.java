package com.ef.emergency.persistence;

import com.ef.emergency.dto.IncidentType;
import com.ef.emergency.dto.Location;
import com.ef.emergency.dto.SeverityLevel;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class IncidentPersistenceEntity {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private IncidentType type;
    @Embedded
    private Location location;
    private Instant timestamp;
    @Enumerated(EnumType.STRING)
    private SeverityLevel severityLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IncidentType getType() {
        return type;
    }

    public void setType(IncidentType type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public SeverityLevel getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }
}
