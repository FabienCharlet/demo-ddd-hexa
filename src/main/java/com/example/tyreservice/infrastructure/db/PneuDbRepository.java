package com.example.tyreservice.infrastructure.db;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.stereotype.Component;

import com.example.tyreservice.application.repositories.PneuRepository;
import com.example.tyreservice.domain.Pneu;
import com.example.tyreservice.infrastructure.db.jpa.PneuJpaRepository;
import com.example.tyreservice.infrastructure.db.jpa.entities.PneuDbEntity;

@Component
public class PneuDbRepository implements PneuRepository {

	private final PneuJpaRepository jpaRepository;
	private final ModelMapper mapper;

	public PneuDbRepository(final PneuJpaRepository jpaRepository) {

		this.jpaRepository = jpaRepository;
		mapper = new ModelMapper();
		mapper.getConfiguration()
	        .setFieldMatchingEnabled(true)
	        .setFieldAccessLevel(AccessLevel.PRIVATE);
	}


	@Override
	public Set<Pneu> findAll() {

		return mapToDomainEntities(jpaRepository.findAll());
	}


	@Override
	public Set<Pneu> findByIds(final Set<UUID> ids) {

		return mapToDomainEntities(jpaRepository.findAllById(ids));
	}

	private Set<Pneu> mapToDomainEntities(final Iterable<PneuDbEntity> pneusFromDb) {

		return StreamSupport.stream(pneusFromDb.spliterator(), false)
						.map(dbEntity -> mapFrom(dbEntity)).collect(Collectors.toSet());
	}

	@SuppressWarnings("unused")
	private Set<PneuDbEntity> mapToDbEntities(final Iterable<Pneu> pneusFromDomain) {

		return StreamSupport.stream(pneusFromDomain.spliterator(), false)
						.map(domainEntity -> mapFrom(domainEntity)).collect(Collectors.toSet());
	}

	private PneuDbEntity mapFrom(final Pneu domainEntity) {

		return mapper.map(domainEntity, PneuDbEntity.class);

	}

	private Pneu mapFrom(final PneuDbEntity dbEntity) {

		try {
			final Class<?>builderClass = Class.forName("com.example.tyreservice.domain.Pneu$PneuBuilder");

			final Object mappedBuilder = mapper.map(dbEntity, builderClass);

			final Method buidMethod = builderClass.getDeclaredMethod("build");

			buidMethod.setAccessible(true);
			return (Pneu) buidMethod.invoke(mappedBuilder);

		} catch (final Exception e) {

			throw new RuntimeException(e);
		}
	}


	@Override
	public Pneu save(final Pneu pneu) {

		final PneuDbEntity dbEntity = mapFrom(pneu);

		final PneuDbEntity savedEntity = jpaRepository.save(dbEntity);

		return mapFrom(savedEntity);
	}

}
