package com.example.tyreservice.application.repositories;

import java.util.Set;

import com.example.tyreservice.domain.Vehicule;

public interface VehiculeRepository {

	Set<Vehicule> findAll();
}
