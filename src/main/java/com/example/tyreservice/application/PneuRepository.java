package com.example.tyreservice.application;

import java.util.Set;
import java.util.UUID;

import com.example.tyreservice.domain.Pneu;

public interface PneuRepository {

	Set<Pneu> findAll();

	Set<Pneu> findByIds(Set<UUID> ids);
}
