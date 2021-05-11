package br.com.projeto.apirest.api.dto.matricula;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import br.com.projeto.apirest.api.model.Matricula;


public class MatriculaResponse {
	
	private Long id;
	private Long alunoID;
	private Long cursoID;
	private Boolean aprovado;
	private LocalDate dataDeInscricao;
	
	public MatriculaResponse() {}

	public MatriculaResponse(Matricula m) {
		this.id = m.getId();
		this.aprovado = m.getAprovado();
		this.dataDeInscricao = m.getDataDeInscricao();
		this.alunoID = m.getAluno().getId();
		this.cursoID = m.getCurso().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getAlunoID() {
		return alunoID;
	}

	public void setAlunoID(Long alunoID) {
		this.alunoID = alunoID;
	}

	public Long getCursoID() {
		return cursoID;
	}

	public void setCursoID(Long cursoID) {
		this.cursoID = cursoID;
	}

	public Boolean getAprovado() {
		return aprovado;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}

	public LocalDate getDataDeInscricao() {
		return dataDeInscricao;
	}

	public void setDataDeInscricao(LocalDate dataDeInscricao) {
		this.dataDeInscricao = dataDeInscricao;
	}
	
	
	public static Page<MatriculaResponse> converterParaDto(Page<Matricula> matriculas){
		return matriculas.map(MatriculaResponse::new);
	}
	
	
	public static List<MatriculaResponse> converterParaDto(List<Matricula> matriculas) {
		
		List<MatriculaResponse> dto = new ArrayList<>();
		
		matriculas.forEach(matricula -> dto.add(new MatriculaResponse(matricula)));
		
		return dto;
	}
	
	
}
