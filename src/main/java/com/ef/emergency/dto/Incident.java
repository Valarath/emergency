package com.ef.emergency.dto;

public record Incident(
        String id,
        IncidentType type,
        Location location,
        java.time.Instant timestamp,
        SeverityLevel severityLevel)
{ }
