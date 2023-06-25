package com.example.tyreservice.infrastructure.rest;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tyreservice.application.PneuRepository;
import com.example.tyreservice.domain.Pneu;

@RestController
@RequestMapping("/pneu")
public class PneuRestController {

	private final PneuRepository repository;

	private PneuRestController(final PneuRepository repository) {

		this.repository = repository;
	}

	@GetMapping("/all")
	public Set<Pneu> findAll() {

		return repository.findAll();
	}
}
