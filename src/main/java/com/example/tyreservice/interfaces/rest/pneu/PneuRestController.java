package com.example.tyreservice.interfaces.rest.pneu;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tyreservice.application.PneuService;
import com.example.tyreservice.domain.Pneu;

@RestController
@RequestMapping("/pneu")
public class PneuRestController {

	private final PneuService service;

	private PneuRestController(final PneuService service) {

		this.service = service;
	}

	@PostMapping
	public Pneu create(@RequestBody final PneuCreationCommand command) {

		return service.nouvelleReference(command.getMarque(), command.getModele(), command.getTaille(), command.getPrix());
    }

	@GetMapping("/all")
	public Set<Pneu> findAll() {

		return service.findAll();
	}
}
