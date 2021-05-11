package br.com.projeto.apirest.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.projeto.apirest.api.exceptions.ObjectNotFoundException;
import br.com.projeto.apirest.api.model.Curso;
import br.com.projeto.apirest.api.model.Unidade;
import br.com.projeto.apirest.api.repository.UnidadeRepository;

@Service
public class UnidadeService {
	
	@Autowired
	private UnidadeRepository unidadeRepository;
	
	
	public Page<Unidade> findAll(Pageable pageable){
		return unidadeRepository.findAll(pageable);
	}
	
	public Unidade findById(Long id) {
		return unidadeRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Resource Unidade (id: " + id + ") not found"));
	}
	
	public Unidade findByNome(String nome) {
		return unidadeRepository.findByNome(nome).orElseThrow(()-> new ObjectNotFoundException("Resource Unidade (nome: " + nome + ") not found"));
	}
	
	public Unidade save(Unidade unidade) {
		return unidadeRepository.save(unidade);
	}
	
	public void deleteById(Long id) {
		unidadeRepository.deleteById(id);
	}
	
	public Page<Curso> findCursos(Long id, Pageable pageable){
		return unidadeRepository.findCursos(id, pageable);
	}

	public Page<Unidade> findByNomeContaining(String nome, Pageable pageable) {
		return unidadeRepository.findByNomeContaining(nome, pageable);
	}
	
}
