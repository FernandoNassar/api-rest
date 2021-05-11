package br.com.projeto.apirest.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.projeto.apirest.api.model.Curso;
import br.com.projeto.apirest.api.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	
	@Query("SELECT c FROM Curso c WHERE c.professor.id = :id")
	Page<Curso> findCursos(Long id, Pageable pageable);
	
	
	Page<Professor> findByCpf(String cpf, Pageable pageable);


	Page<Professor> findByNomeContaining(String nome, Pageable pageable);

}
