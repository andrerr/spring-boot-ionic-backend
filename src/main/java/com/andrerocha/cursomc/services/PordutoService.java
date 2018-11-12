package com.andrerocha.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.andrerocha.cursomc.domain.Categoria;
import com.andrerocha.cursomc.domain.Produto;
import com.andrerocha.cursomc.repositories.CategoriaRepository;
import com.andrerocha.cursomc.repositories.ProdutoRepository;
import com.andrerocha.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PordutoService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository repo;

	public Produto find(Integer id) {
		Produto obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Não foi possivel retornar dados. " + id 
					+ " Tipo: " + " Categoria: " + Produto.class.getName());
		}
		return obj;
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPages, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPages, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAll(ids);
		return repo.search(nome, categorias, pageRequest);
	}
}