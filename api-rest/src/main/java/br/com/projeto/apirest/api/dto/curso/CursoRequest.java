package br.com.projeto.apirest.api.dto.curso;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import br.com.projeto.apirest.api.model.Curso;
import br.com.projeto.apirest.api.services.ProfessorService;
import br.com.projeto.apirest.api.services.UnidadeService;

public class CursoRequest {
	
	@NotBlank
	private String nome;
	private Long unidadeID;
	private Long professorID;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private Boolean finalizado;
	
	
	public CursoRequest() {}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Long getUnidadeID() {
		return unidadeID;
	}

	public void setUnidadeID(Long unidadeID) {
		this.unidadeID = unidadeID;
	}

	public Long getProfessorID() {
		return professorID;
	}

	public void setProfessorID(Long professorID) {
		this.professorID = professorID;
	}
	
	
	public Curso toCurso(ProfessorService professorService, UnidadeService unidadeService) {
		
		return atualizar(new Curso(), professorService, unidadeService);
		
	}
	
	public Curso atualizar(Curso curso, ProfessorService professorService, UnidadeService unidadeService) {
		
		curso.setNome(nome);
		curso.setDataInicio(dataInicio);
		curso.setDataFim(dataFim);
		curso.setFinalizado(finalizado);
		curso.setProfessor(professorService.findById(professorID));
		curso.setUnidade(unidadeService.findById(unidadeID));
		
		return curso;
		
	}
	
	
	
	
}
