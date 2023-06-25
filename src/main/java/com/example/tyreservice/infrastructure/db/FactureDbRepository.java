package com.example.tyreservice.infrastructure.db;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.tyreservice.application.FactureRepository;
import com.example.tyreservice.domain.Facture;
import com.example.tyreservice.infrastructure.db.jpa.FactureJpaRepository;
import com.example.tyreservice.infrastructure.db.jpa.entities.FactureDbEntity;

@Component
public class FactureDbRepository implements FactureRepository {

	private final FactureJpaRepository jpaRepository;
	private final ModelMapper mapper;

	public FactureDbRepository(final FactureJpaRepository jpaRepository) {

		this.jpaRepository = jpaRepository;
		mapper = new ModelMapper();
	}


	@Override
	public Facture save(final Facture facture) {

		final FactureDbEntity dbEntity = mapper.map(facture, FactureDbEntity.class);

		final FactureDbEntity savedEntity = jpaRepository.save(dbEntity);

		return mapper.map(savedEntity, Facture.class);
	}

}
