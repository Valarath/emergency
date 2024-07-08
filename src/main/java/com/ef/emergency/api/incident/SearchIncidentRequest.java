package com.ef.emergency.api.incident;

import com.ef.emergency.dto.Page;
import com.ef.emergency.dto.SearchFilter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record SearchIncidentRequest(
        @Valid Page page,
        @Valid @NotNull SearchFilter filter)
{
    public SearchIncidentRequest {
        page = new Page();
    }
}
