package br.com.projeto.apirest.api.dto.avaliacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import br.com.projeto.apirest.api.model.Avaliacao;
import br.com.projeto.apirest.api.model.TipoAvaliacao;


public class AvaliacaoResponse {
	
	private Long id;
	private Long matriculaID;
	private TipoAvaliacao tipoAvaliacao;
	private Double nota;
	private LocalDate data;
	
	
	public AvaliacaoResponse() {}
	
	public AvaliacaoResponse(Avaliacao avaliacao) {
		this.id = avaliacao.getId();
		this.matriculaID = avaliacao.getMatricula().getId();
		this.tipoAvaliacao = avaliacao.getTipoAvaliacao();
		this.nota = avaliacao.getNota();
		this.data = avaliacao.getData();
	}
	

	public Long getMatriculaID() {
		return matriculaID;
	}

	public void setMatriculaID(Long matriculaID) {
		this.matriculaID = matriculaID;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	
	public static List<AvaliacaoResponse> converterParaDto(List<Avaliacao> avaliacoes){
		List<AvaliacaoResponse> dto = new ArrayList<>();
		
		for(Avaliacao a : avaliacoes) {
			dto.add(new AvaliacaoResponse(a));
		}
		
		return dto;
	}
	
	public static Page<AvaliacaoResponse> converterParaDto(Page<Avaliacao> avaliacoes){
		return avaliacoes.map(AvaliacaoResponse::new);
	}
	
	
}
