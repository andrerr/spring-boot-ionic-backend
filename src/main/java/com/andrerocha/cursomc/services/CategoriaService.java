package com.andrerocha.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrerocha.cursomc.domain.Categoria;
import com.andrerocha.cursomc.repositories.CategoriaRepository;
import com.andrerocha.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Não foi possivel retornar dados. " + id 
					+ " Tipo: " + " Categoria: " + Categoria.class.getName());
		}
		return obj;
	}
	
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
}