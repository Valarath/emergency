package com.ef.emergency.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentPersistenceRepository extends JpaRepository<IncidentPersistenceEntity, String> {
}
