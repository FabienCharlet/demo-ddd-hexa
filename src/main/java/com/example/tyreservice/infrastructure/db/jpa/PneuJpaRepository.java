package com.example.tyreservice.infrastructure.db.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tyreservice.infrastructure.db.jpa.entities.PneuDbEntity;

@Repository
public interface PneuJpaRepository extends CrudRepository<PneuDbEntity, UUID> {

}
