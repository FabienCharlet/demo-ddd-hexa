package com.example.tyreservice.infrastructure.db.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tyreservice.infrastructure.db.jpa.entities.FactureDbEntity;

@Repository
public interface FactureJpaRepository extends CrudRepository<FactureDbEntity, UUID> {

}
