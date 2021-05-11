package br.com.projeto.apirest.api.dto.curso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.projeto.apirest.api.model.Curso;

public class CursoResponse {
	
	private Long id;
	private Long unidadeID;
	private Long professorID;
	private String nome;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private Boolean finalizado;
	
	
	public CursoResponse() {}
	
	public CursoResponse(Curso curso) {
		this.id = curso.getId();
		this.nome = curso.getNome();
		this.dataInicio = curso.getDataInicio();
		this.dataFim = curso.getDataFim();
		this.finalizado = curso.getFinalizado();
		this.professorID = curso.getProfessor().getId();
		this.unidadeID = curso.getUnidade().getId();
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

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	
	public static Page<CursoResponse> toDto(Page<Curso> curso){
		return curso.map(CursoResponse::new);
	}
	
	public static List<CursoResponse> toDto(List<Curso> cursos) {
		List<CursoResponse> dto = new ArrayList<>();
		cursos.forEach(curso -> dto.add(new CursoResponse(curso)));
		
		return dto;
	}
	
}
