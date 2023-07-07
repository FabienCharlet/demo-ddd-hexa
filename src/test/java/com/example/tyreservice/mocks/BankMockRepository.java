package com.example.tyreservice.mocks;

import java.util.HashMap;
import java.util.Map;

import com.example.tyreservice.application.repositories.BankRepository;

public class BankMockRepository implements BankRepository {

	public Map<String, Integer> debits = new HashMap<>();

	@Override
	public void debiter(final String carteBleue, final int montant) {

		debits.put(carteBleue, montant);
	}


}
