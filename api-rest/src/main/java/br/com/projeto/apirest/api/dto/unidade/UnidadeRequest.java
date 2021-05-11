package br.com.projeto.apirest.api.dto.unidade;

import javax.validation.constraints.NotBlank;
import br.com.projeto.apirest.api.model.Unidade;

public class UnidadeRequest {
	
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String endereco;
	@NotBlank 
	private String cep;
	
	
	public UnidadeRequest() {}
	
	public UnidadeRequest(Unidade unidade) {
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
	
	public Unidade toUnidade() {
		return atualizar(new Unidade());
	}
	
	public Unidade atualizar(Unidade unidade) {
		unidade.setNome(nome);
		unidade.setCep(cep);
		unidade.setEndereco(endereco);	
		return unidade;
	}
	
}
