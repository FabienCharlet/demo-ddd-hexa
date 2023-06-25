package com.example.tyreservice.application.repositories;

import java.util.Set;

import com.example.tyreservice.domain.Client;

public interface ClientRepository {

	Set<Client> findAll();
}
