package br.com.projeto.apirest.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.apirest.api.model.Curso;
import br.com.projeto.apirest.api.model.Unidade;

@Repository
public interface UnidadeRepository extends PagingAndSortingRepository<Unidade, Long>{
	Optional<Unidade> findByNome(String nome);
	
	@Query("SELECT c FROM Curso c WHERE c.unidade.id = :id")
	Page<Curso> findCursos(Long id, Pageable pageable);

	Page<Unidade> findByNomeContaining(String nome, Pageable pageable);
	
}
