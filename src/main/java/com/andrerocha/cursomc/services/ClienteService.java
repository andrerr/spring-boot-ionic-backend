package com.andrerocha.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.andrerocha.cursomc.domain.Cidade;
import com.andrerocha.cursomc.domain.Cliente;
import com.andrerocha.cursomc.domain.Endereco;
import com.andrerocha.cursomc.domain.enums.TipoCliente;
import com.andrerocha.cursomc.dto.ClienteDTO;
import com.andrerocha.cursomc.dto.ClienteNewDTO;
import com.andrerocha.cursomc.repositories.CidadeRepository;
import com.andrerocha.cursomc.repositories.ClienteRepository;
import com.andrerocha.cursomc.repositories.EnderecoRepository;
import com.andrerocha.cursomc.services.exception.DataIntegrityException;
import com.andrerocha.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

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
		obj = repo.save(obj);
		enderecoRepository.save(obj.getEnderecos()); //precisa dessa parte para poder inserir um endereço ao cliente;
		return obj;
	}
	
	public Cliente update (Cliente obj) {
		Cliente newOblj = find(obj.getId());
		updateData(newOblj, obj);
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
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipoCliente()));
		Cidade cid = cidadeRepository.findOne(objDTO.getCidadeId());
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		} if (objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
	}

	public void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}