package br.com.projeto.apirest.api.dto.professor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.projeto.apirest.api.model.Professor;

public class ProfessorResponse {
	
	private Long id;
	private String nome;
	private String cpf;
	private LocalDate dataContratacao;
//	private List<CursoProfessorDto> cursos;
	
	public ProfessorResponse() {}
	
	public ProfessorResponse(Professor p) {
		this.id = p.getId();
		this.nome = p.getNome();
		this.cpf = p.getCpf();
		this.dataContratacao = p.getDataDeContratacao();
//		this.cursos = CursoProfessorDto.toDto(p.getCursos());
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
	public LocalDate getDataContratacao() {
		return dataContratacao;
	}
	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}
//	public List<CursoProfessorDto> getCursos() {
//		return cursos;
//	}
//	public void setCursos(List<CursoProfessorDto> cursos) {
//		this.cursos = cursos;
//	}
	
	public Professor toProfessor() {
		
		Professor p = new Professor();
		p.setNome(nome);
		p.setCpf(cpf);
		p.setDataDeContratacao(dataContratacao);
		
		return p;
		
	}
	
	public static Page<ProfessorResponse> toDto(Page<Professor> professores){
		return professores.map(ProfessorResponse::new);
	}
	
	public static List<ProfessorResponse> toDto(List<Professor> professores) {
		List<ProfessorResponse> dto = new ArrayList<>();
		for(Professor p : professores) {
			dto.add(new ProfessorResponse(p));
		}
		return dto;
	}
	

	
}
