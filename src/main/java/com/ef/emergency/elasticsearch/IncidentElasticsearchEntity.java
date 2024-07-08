package com.ef.emergency.elasticsearch;

import com.ef.emergency.dto.IncidentType;
import com.ef.emergency.dto.SeverityLevel;
import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.time.Instant;

@Document(indexName = "incident")
public class IncidentElasticsearchEntity {
    @Id
    private String id;
    @Field(type = FieldType.Keyword)
    private IncidentType type;
    @GeoPointField
    private GeoPoint location;
    @Field(type = FieldType.Date_Nanos)
    private Instant timestamp;
    @Field(type = FieldType.Keyword)
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

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
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
