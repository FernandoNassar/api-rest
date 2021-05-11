package br.com.projeto.apirest.api.dto.matricula;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import br.com.projeto.apirest.api.model.Matricula;
import br.com.projeto.apirest.api.services.AlunoService;
import br.com.projeto.apirest.api.services.CursoService;

public class MatriculaRequest {

	@NotNull
	private Long alunoID;
	@NotNull
	private Long cursoID;
	
	private Boolean aprovado;
	@NotNull
	private LocalDate dataDeInscricao;
	
	
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
	public Long getAlunoID() {
		return alunoID;
	}
	public void setAlunoID(Long aluno) {
		this.alunoID = aluno;
	}
	public Long getCursoID() {
		return cursoID;
	}
	public void setCursoID(Long curso) {
		this.cursoID = curso;
	}
	
	public Matricula toMatricula(AlunoService alunoService, CursoService cursoService) {
		
		return atualizar(new Matricula(), alunoService, cursoService);

	}
	
	public Matricula atualizar(Matricula matricula, AlunoService alunoService, CursoService cursoService) {
		
		matricula.setAprovado(aprovado);
		matricula.setDataDeInscricao(dataDeInscricao);
		matricula.setAluno(alunoService.findById(alunoID));
		matricula.setCurso(cursoService.findById(cursoID));
		
		return matricula;
	}
	
}
