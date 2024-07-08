package com.ef.emergency.api.incident;

import com.ef.emergency.dto.Incident;
import com.ef.emergency.service.IncidentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incident")
public class IncidentApi {

    private final IncidentService incidentService;

    public IncidentApi(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping()
    public Incident log(@Valid @RequestBody LogIncidentRequest request){
        return incidentService.log(request.location(), request.type(), request.severity());
    }

    @GetMapping()
    public List<Incident> search(@Valid SearchIncidentRequest request){
        return incidentService.search(request.page(), request.filter());
    }
}
