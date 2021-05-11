package br.com.projeto.apirest.api.dto.avaliacao;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import br.com.projeto.apirest.api.model.Avaliacao;
import br.com.projeto.apirest.api.model.TipoAvaliacao;
import br.com.projeto.apirest.api.services.MatriculaService;

public class AvaliacaoRequest {
	
	@NotNull
	private TipoAvaliacao tipoAvaliacao;
	@NotNull
	private Double nota;
	@NotNull
	private LocalDate data;
	@NotNull
	private Long matriculaID;
	
	
	public TipoAvaliacao getTipoAvaliacao() {
		return tipoAvaliacao;
	}
	public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
		this.tipoAvaliacao = tipoAvaliacao;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Long getMatriculaID() {
		return matriculaID;
	}
	public void setMatriculaID(Long matricula) {
		this.matriculaID = matricula;
	}
	
	
	public Avaliacao toAvaliacao(MatriculaService matriculaService) {				
		return atualizar(new Avaliacao(), matriculaService);
	}
	
	public Avaliacao atualizar(Avaliacao a, MatriculaService matriculaService) {
		
		a.setTipoAvaliacao(tipoAvaliacao);
		a.setNota(nota);
		a.setData(data);
		a.setMatricula(matriculaService.findById(matriculaID));
		
		return a;
	}
	
}
