package com.andrerocha.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.andrerocha.cursomc.domain.Categoria;
import com.andrerocha.cursomc.dto.CategoriaDTO;
import com.andrerocha.cursomc.repositories.CategoriaRepository;
import com.andrerocha.cursomc.services.exception.DataIntegrityException;
import com.andrerocha.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Não foi possivel retornar dados. " + id 
					+ " Tipo: " + " Categoria: " + Categoria.class.getName());
		}
		return obj;
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public void delete (Integer id	) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir categorias com produtos.");
		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPages, String orderBy, String direction) {
			PageRequest pageRequest = new PageRequest(page, linesPages, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDto(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateDate(newObj, obj);
		return repo.save(newObj);
	}
	
	public void updateDate(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
}