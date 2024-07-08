package com.ef.emergency.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;
import java.util.Set;

public class SearchFilter {

    private Set<IncidentType> incidentTypes = Set.of(IncidentType.values());
    private Set<SeverityLevel> severities = Set.of(SeverityLevel.values());
    @PastOrPresent
    private Instant from = Instant.now();
    private Instant to = Instant.now();
    @Valid
    @NotNull
    private Location location;
    @DecimalMin(value = "0", inclusive = false)
    private int distance = 1;
    private DistanceUnit distanceUnit = DistanceUnit.Kilometers;

    @AssertTrue(message = "from must be before to")
    public boolean isFromBeforeTo() {
        return from.isBefore(to) || from.equals(to);
    }

    public Set<IncidentType> getIncidentTypes() {
        return incidentTypes;
    }

    public void setIncidentTypes(Set<IncidentType> incidentTypes) {
        this.incidentTypes = incidentTypes;
    }

    public Set<SeverityLevel> getSeverities() {
        return severities;
    }

    public void setSeverities(Set<SeverityLevel> severities) {
        this.severities = severities;
    }

    public Instant getFrom() {
        return from;
    }

    public void setFrom(Instant from) {
        this.from = from;
    }

    public Instant getTo() {
        return to;
    }

    public void setTo(Instant to) {
        this.to = to;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public DistanceUnit getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(DistanceUnit distanceUnit) {
        this.distanceUnit = distanceUnit;
    }
}
