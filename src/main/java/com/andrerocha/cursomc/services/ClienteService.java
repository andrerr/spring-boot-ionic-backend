package com.andrerocha.cursomc.services;

import java.util.List;
import java.util.function.ObjLongConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.andrerocha.cursomc.domain.Cliente;
import com.andrerocha.cursomc.dto.ClienteDTO;
import com.andrerocha.cursomc.repositories.ClienteRepository;
import com.andrerocha.cursomc.services.exception.DataIntegrityException;
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
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Cliente insert (Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Cliente update (Cliente obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete (Integer id	) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque existem entidades relacionadas.");
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPages, String orderBy, String direction) {
			PageRequest pageRequest = new PageRequest(page, linesPages, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getCpfOuCnpj(), null, null);
	}
}