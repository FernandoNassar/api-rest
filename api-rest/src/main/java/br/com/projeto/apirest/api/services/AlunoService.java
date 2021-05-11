package br.com.projeto.apirest.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.projeto.apirest.api.exceptions.ObjectNotFoundException;
import br.com.projeto.apirest.api.model.Aluno;
import br.com.projeto.apirest.api.model.Avaliacao;
import br.com.projeto.apirest.api.model.Matricula;
import br.com.projeto.apirest.api.repository.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	
	public Page<Aluno> findAll(Pageable pageable){
		return alunoRepository.findAll(pageable);
	}
	
	
	public Aluno findById(Long id) {
		return alunoRepository.findById(id).get();
	}
	
	
	public void deleteByid(Long id) {
		try {
			alunoRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Aluno (id: " + id + ") not found");
		}
	}
	
	
	public Aluno save(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	
	public Page<Matricula> findMatriculas(Long id, Pageable pageable){
		return alunoRepository.findMatriculas(id, pageable);
	}
	
	public Page<Avaliacao> findAvaliacoes(Long id, Pageable pageable){
		return alunoRepository.findAvaliacoes(id, pageable);
	}
	
	public Page<Aluno> findByCpf(String cpf, Pageable pageable){
		return alunoRepository.findByCpf(cpf, pageable);
	}


	public Page<Aluno> findByNomeContaining(String nome, Pageable pageable) {
		return alunoRepository.findByNomeContaining(nome, pageable);
	}
	
}
