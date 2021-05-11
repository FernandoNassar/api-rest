package br.com.projeto.apirest.api.dto.aluno;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.projeto.apirest.api.model.Aluno;

public class AlunoRequest {
	
	@NotBlank
	private String nome;
	@CPF
	private String cpf;
	@NotNull
	private LocalDate dataDeInscricao;
	
	
	public AlunoRequest() {}
	
	public AlunoRequest(Aluno aluno) {
		this.nome = aluno.getNome();
		this.cpf = aluno.getCpf();
		this.dataDeInscricao = aluno.getDataDeInscricao();	
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataDeInscricao() {
		return dataDeInscricao;
	}
	public void setDataDeInscricao(LocalDate dataDeInscricao) {
		this.dataDeInscricao = dataDeInscricao;
	}
	
	public Aluno toAluno() {
		return atualizar(new Aluno());
	}
	
	public Aluno atualizar(Aluno aluno) {
		
		aluno.setNome(nome);
		aluno.setCpf(cpf);
		aluno.setDataDeInscricao(dataDeInscricao);
		
		return aluno;
	}
	
}
