package br.com.projeto.apirest.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.apirest.api.model.Avaliacao;
import br.com.projeto.apirest.api.model.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
	
	@Query("SELECT a FROM Avaliacao a WHERE a.matricula.id = :id")
	Page<Avaliacao> findAvaliacoes(Long id, Pageable pageable);
	
	
	@Query(nativeQuery = true, value = "SELECT * FROM matricula WHERE aluno_id = :alunoID")
	Page<Matricula> findByAlunoID(Long alunoID, Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM matricula WHERE curso_id = :cursoID")
	Page<Matricula> findByCursoID(Long cursoID, Pageable pageable);




}
