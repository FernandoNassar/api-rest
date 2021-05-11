package br.com.projeto.apirest.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.projeto.apirest.api.exceptions.ObjectNotFoundException;
import br.com.projeto.apirest.api.model.Curso;
import br.com.projeto.apirest.api.model.Matricula;
import br.com.projeto.apirest.api.repository.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	
	public Page<Curso> findAll(Pageable pageable){
		return cursoRepository.findAll(pageable);
	}
	
	public Curso findById(Long id) {
		return cursoRepository.findById(id).get();
	}

	public Curso save(Curso curso) {
		return cursoRepository.save(curso);
		
	}

	public void deleteById(Long id) {
		try {			
			cursoRepository.deleteById(id);			
			
		} catch (EmptyResultDataAccessException e) {		
			throw new ObjectNotFoundException("Curso (id: " + id + " Not Found");			
		}
		
	}
	
	public Page<Matricula> findMatriculas(Long id, Pageable pageable){
		return cursoRepository.findMatriculas(id, pageable);
	}

	public Page<Curso> findByNomeContaining(String nome, Pageable pageable) {
		return cursoRepository.findByNomeContaining(nome, pageable);
	}
	
	public Page<Curso> findByProfessorID(Long id, Pageable pageable){
		return cursoRepository.findByProfessorID(id, pageable);
	}

	
}
