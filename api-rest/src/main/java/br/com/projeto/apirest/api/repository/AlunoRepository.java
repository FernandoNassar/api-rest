package br.com.projeto.apirest.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.apirest.api.model.Aluno;
import br.com.projeto.apirest.api.model.Avaliacao;
import br.com.projeto.apirest.api.model.Matricula;

@Repository
public interface AlunoRepository extends PagingAndSortingRepository<Aluno, Long> {
	
	@Query("SELECT m FROM Matricula m WHERE m.aluno.id = :id")
	Page<Matricula> findMatriculas(Long id, Pageable pageable);
	
//	@Query("SELECT a FROM Avaliacao a WHERE a.aluno.id = :id")
//	Page<Avaliacao> findAvaliacoes(Long id, Pageable pageable);
	
//	@Query(value = "SELECT * FROM avaliacao a JOIN matricula m ON a.matriula_id = m.id WHERE m.id = :id", nativeQuery = true)
	@Query(value = "SELECT a FROM Avaliacao a WHERE a.matricula.aluno.id = :id")
	Page<Avaliacao> findAvaliacoes(Long id, Pageable pageable);
	
	
	Page<Aluno> findByCpf(String cpf, Pageable pageable);

	Page<Aluno> findByNomeContaining(String nome, Pageable pageable);
	
	
}
