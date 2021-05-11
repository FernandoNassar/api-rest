package br.com.projeto.apirest.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.projeto.apirest.api.exceptions.ObjectNotFoundException;
import br.com.projeto.apirest.api.model.Avaliacao;
import br.com.projeto.apirest.api.model.Matricula;
import br.com.projeto.apirest.api.repository.MatriculaRepository;

@Service
public class MatriculaService {
	
	@Autowired
	private MatriculaRepository matriculaRepository;
	
	
	public Page<Matricula> findAll(Pageable pageable){
		return matriculaRepository.findAll(pageable);
	}
	
	public Matricula findById(Long id) {
		return matriculaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Resource Matricula (id: " + id + ") not found"));
	}
	
	public Matricula save(Matricula matricula) {
		return matriculaRepository.save(matricula);
	}
	
	public void deleteById(Long id) {
		try {
			matriculaRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Matricula (id: " + id + ") not found");
		}
	}
	
	public Page<Avaliacao> findAvaliacoes(Long id, Pageable pageable){
		return matriculaRepository.findAvaliacoes(id, pageable);
	}

	public Page<Matricula> findByAlunoID(Long alunoID, Pageable pageable) {
		return matriculaRepository.findByAlunoID(alunoID, pageable);
	}

	public Page<Matricula> findByCursoID(Long cursoID, Pageable pageable) {
		return matriculaRepository.findByCursoID(cursoID, pageable);
	}
	
	
}
