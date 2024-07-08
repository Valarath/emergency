package com.ef.emergency.elasticsearch;

import com.ef.emergency.dto.*;
import com.ef.emergency.service.IncidentMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidentElasticSearchService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final IncidentMapper incidentMapper;
    private final ElasticsearchTemplate elasticsearchTemplate;

    public IncidentElasticSearchService(ElasticsearchOperations elasticsearchOperations, IncidentMapper incidentMapper, ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.incidentMapper = incidentMapper;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @PostConstruct
    public void init() {
        IndexOperations indexOperations = elasticsearchTemplate.indexOps(IncidentElasticsearchEntity.class);
        if (indexOperations.exists())
            indexOperations.putMapping(indexOperations.createMapping());
        else
            indexOperations.createMapping();
        indexOperations.refresh();
    }

    public void log(Incident incident) {
        IncidentElasticsearchEntity incidentElasticsearchEntity = incidentMapper.toIncidentElasticsearchEntity(incident);
        elasticsearchOperations.save(incidentElasticsearchEntity);
    }

    public List<Incident> search(Page page, SearchFilter filter) {
        Query query = toQuery(page, filter);
        return elasticsearchOperations.search(query, IncidentElasticsearchEntity.class).stream()
                .map(SearchHit::getContent)
                .map(incidentMapper::toIncident)
                .collect(Collectors.toList());
    }

    private Query toQuery(Page page, SearchFilter filter) {
        PageRequest pageRequest = toPageRequest(page);
        return new NativeQueryBuilder()
                .withQuery(toFilterQuery(filter))
                .withPageable(pageRequest).build();
    }

    private PageRequest toPageRequest(Page page) {
        return PageRequest.of(page.getPageNumber(), page.getPageSize());
    }

    private CriteriaQuery toFilterQuery(SearchFilter filter) {
        Criteria criteria = new Criteria("location")
                .within(incidentMapper.toGeoPoint(filter.getLocation()), filter.getDistance()+ filter.getDistanceUnit().getShortcut())
                .and("timestamp").between(filter.getFrom(), filter.getTo())
                .and("type").in(filter.getIncidentTypes())
                .and("severityLevel").in(filter.getSeverities());
        return new CriteriaQuery(criteria);
    }
}
