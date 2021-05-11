package br.com.projeto.apirest.api.dto.aluno;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import br.com.projeto.apirest.api.model.Aluno;

public class AlunoResponse {
	
	private Long id;
	private String nome;
	private String cpf;
	private LocalDate dataDeInscricao;
	
	
	public AlunoResponse() {}
	
	public AlunoResponse(Aluno aluno) {
		this.id = aluno.getId();
		this.nome = aluno.getNome();
		this.cpf = aluno.getCpf();
		this.dataDeInscricao = aluno.getDataDeInscricao();
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
		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setCpf(cpf);
		aluno.setDataDeInscricao(dataDeInscricao);
		return aluno;
	}
	
	public static Page<AlunoResponse> converterParaDto(Page<Aluno> alunos){
		return alunos.map(AlunoResponse::new);
	}
	
	
}
