package br.com.projeto.apirest.api.dto.unidade;

import org.springframework.data.domain.Page;

import br.com.projeto.apirest.api.model.Unidade;

public class UnidadeResponse {
	private Long id;
	private String nome;
	private String endereco;
	private String cep;
	
	
	public UnidadeResponse() {}
	
	public UnidadeResponse(Unidade unidade) {
		this.id = unidade.getId();
		this.nome = unidade.getNome();
		this.endereco = unidade.getEndereco();
		this.cep = unidade.getCep();
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public static Page<UnidadeResponse> converterParaDto(Page<Unidade> unidades){
		return unidades.map(UnidadeResponse::new);
	}
	
	
}
