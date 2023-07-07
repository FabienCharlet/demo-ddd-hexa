package com.example.tyreservice.infrastructure.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.tyreservice.application.repositories.BankRepository;

@Component
public class BankRestAdapter implements BankRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(BankRestAdapter.class);

	@Override
	public void debiter(final String carteBleue, final int montant) {

		// For the simplicity of this tutorial, we don't implement a real bank ;)
		// But you coiuld implement a REST call here

		LOGGER.info("Bank accepted payment");
	}


}
