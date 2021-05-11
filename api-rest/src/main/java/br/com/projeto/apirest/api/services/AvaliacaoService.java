package br.com.projeto.apirest.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.projeto.apirest.api.exceptions.ObjectNotFoundException;
import br.com.projeto.apirest.api.model.Avaliacao;
import br.com.projeto.apirest.api.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	
	public Page<Avaliacao> findAll(Pageable pageable){
		return avaliacaoRepository.findAll(pageable);
	}
	
	public Avaliacao findById(Long id) {
		return avaliacaoRepository.findById(id).get();
	}
	
	public Avaliacao save(Avaliacao avaliacao) {
		return avaliacaoRepository.save(avaliacao);
	}
	
	public void deleteById(Long id) {
		
		try {
			avaliacaoRepository.deleteById(id);
			
		} catch(EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Avaliação (id: " + id + ") not found");
		}
		
	}

	public Page<Avaliacao> findByAlunoID(Long alunoID, Pageable pageable) {
		return avaliacaoRepository.findByAlunoID(alunoID, pageable);
	}

	public Page<Avaliacao> findByCursoID(Long cursoID, Pageable pageable) {
		return avaliacaoRepository.findByCursoID(cursoID, pageable);
	}
	
	
}
