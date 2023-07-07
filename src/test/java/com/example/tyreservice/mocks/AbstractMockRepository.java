package com.example.tyreservice.mocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.tyreservice.domain.DomainEntity;

public class AbstractMockRepository<T extends DomainEntity> {

	public Map<UUID, T> mockDatabase = new HashMap<>();

	public Set<T> findAll() {

		return Set.copyOf(mockDatabase.values());
	}

	public Set<T> findByIds(final Set<UUID> ids) {

		return ids.stream().map(mockDatabase::get).collect(Collectors.toSet());
	}

	public T save(final T instance) {

		return mockDatabase.put(instance.getId(), instance);
	}

}