package com.andrerocha.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrerocha.cursomc.domain.Cliente;
import com.andrerocha.cursomc.repositories.ClienteRepository;
import com.andrerocha.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Não foi possivel retornar dados. " + id 
					+ " Tipo: " + " Cliente: " + Cliente.class.getName());
	}
		return obj;
	}
}