package com.ef.emergency.api.incident;

import com.ef.emergency.dto.IncidentType;
import com.ef.emergency.dto.Location;
import com.ef.emergency.dto.SeverityLevel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public record LogIncidentRequest(
        @NotNull IncidentType type,
        @Valid @NotNull Location location,
        @NotNull SeverityLevel severity)
{ }
