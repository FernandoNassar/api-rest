package br.com.projeto.apirest.api.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"cpf"})})
public class Aluno {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private LocalDate dataDeInscricao;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "aluno")
	private List<Matricula> matriculas;
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "aluno")
//	private List<Avaliacao> avaliacoes;
	
	
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
	public List<Matricula> getMatriculas() {
		return matriculas;
	}
	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
//	public List<Avaliacao> getAvaliacoes() {
//		return avaliacoes;
//	}
//	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
//		this.avaliacoes = avaliacoes;
//	}
	

}
