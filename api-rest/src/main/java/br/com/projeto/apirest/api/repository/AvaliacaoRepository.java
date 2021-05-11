package br.com.projeto.apirest.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.projeto.apirest.api.model.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
	
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM avaliacao a JOIN matricula m ON a.matricula_id=m.id WHERE m.aluno_id = :alunoID")
	Page<Avaliacao> findByAlunoID(Long alunoID, Pageable pageable);
	
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM avaliacao a JOIN matricula m ON a.matricula_id=m.id WHERE m.curso_id = :cursoID")
	Page<Avaliacao> findByCursoID(Long cursoID, Pageable pageable);

}
