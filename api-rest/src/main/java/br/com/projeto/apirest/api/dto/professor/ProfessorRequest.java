package br.com.projeto.apirest.api.dto.professor;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.projeto.apirest.api.model.Professor;

public class ProfessorRequest {
	
	@NotBlank
	private String nome;
	@CPF
	private String cpf;
	@NotNull
	private LocalDate dataContratacao;
	
	
	public ProfessorRequest() {}
	
	public ProfessorRequest(Professor p) {
		this.nome = p.getNome();
		this.cpf = p.getCpf();
		this.dataContratacao = p.getDataDeContratacao();
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
	public LocalDate getDataContratacao() {
		return dataContratacao;
	}
	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}
	
	public Professor toProfessor() {	
		return atualizar(new Professor());	
	}
	
	public Professor atualizar(Professor professor) {
		
		professor.setCpf(cpf);
		professor.setNome(nome);
		professor.setDataDeContratacao(dataContratacao);
		
		return professor;
		
	}

}
