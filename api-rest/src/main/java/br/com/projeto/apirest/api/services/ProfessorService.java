package br.com.projeto.apirest.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.projeto.apirest.api.exceptions.ObjectNotFoundException;
import br.com.projeto.apirest.api.model.Curso;
import br.com.projeto.apirest.api.model.Professor;
import br.com.projeto.apirest.api.repository.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	
	public Page<Professor> findAll(Pageable pageable){
		return professorRepository.findAll(pageable);
	}
	
	public Professor findById(Long id) {
		return professorRepository.findById(id).get();
	}
	
	public Professor save(Professor professor) {
		return professorRepository.save(professor);
	}
	
	public void deleteById(Long id) {
		try {			
			professorRepository.deleteById(id);		
		}
		catch(EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Professor with id: " + id + " not found");
		}
	}
	
	public Page<Curso> findCursos(Long id, Pageable pageable){
		return professorRepository.findCursos(id, pageable);
	}
	
	public Page<Professor> findByCpf(String cpf, Pageable pageable){
		return professorRepository.findByCpf(cpf, pageable);
	}

	public Page<Professor> findByNomeContaining(String nome, Pageable pageable) {
		return professorRepository.findByNomeContaining(nome, pageable);
	}
	
	
}
