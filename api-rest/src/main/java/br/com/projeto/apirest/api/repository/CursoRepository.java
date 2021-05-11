package br.com.projeto.apirest.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.apirest.api.model.Curso;
import br.com.projeto.apirest.api.model.Matricula;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
//	@Query("SELECT c FROM Curso c WHERE c.unidade.id = :id")
//	Page<Curso> findCursos(Long id, Pageable pageable);
	
	//@Query(nativeQuery = true, value = "SELECT * FROM matricula")
	@Query("SELECT m FROM Matricula m WHERE m.curso.id = :id")
	Page<Matricula> findMatriculas(Long id, Pageable pageable);

	Page<Curso> findByNomeContaining(String nome, Pageable pageable);
	
	@Query(nativeQuery = true, value = "SELECT * FROM curso WHERE professor_id = :id")
	Page<Curso> findByProfessorID(Long id, Pageable pageable);

}
