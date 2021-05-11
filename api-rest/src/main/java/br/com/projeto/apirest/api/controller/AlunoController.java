package br.com.projeto.apirest.api.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.projeto.apirest.api.assembler.AlunoAssembler;
import br.com.projeto.apirest.api.assembler.AvaliacaoAssembler;
import br.com.projeto.apirest.api.assembler.MatriculaAssembler;
import br.com.projeto.apirest.api.dto.aluno.AlunoResponse;
import br.com.projeto.apirest.api.dto.avaliacao.AvaliacaoResponse;
import br.com.projeto.apirest.api.dto.matricula.MatriculaResponse;
import br.com.projeto.apirest.api.dto.aluno.AlunoRequest;
import br.com.projeto.apirest.api.model.Aluno;
import br.com.projeto.apirest.api.services.AlunoService;

@RestController @RequestMapping("/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private AlunoAssembler alunoAssembler;
	
	@Autowired
	private MatriculaAssembler matriculaAssembler;
	
	@Autowired
	private AvaliacaoAssembler avaliacaoAssembler;
	
	@Autowired
	private PagedResourcesAssembler<AvaliacaoResponse> avaliacaoResourcesAssembler;
	
	@Autowired 
	private PagedResourcesAssembler<MatriculaResponse> matriculaResourcesAssembler;
	
	@Autowired
	private PagedResourcesAssembler<AlunoResponse> pagedResourcesAssembler;
	
	
	@GetMapping
	public PagedModel<EntityModel<AlunoResponse>> findAll(
			@PageableDefault(sort = "id", direction = Direction.ASC) Pageable pageable, 
			@RequestParam(required = false) String cpf, @RequestParam(required = false) String nome){
		
		Page<Aluno> alunos;
		Page<AlunoResponse> response;
		
		if(cpf != null) {			
			alunos = alunoService.findByCpf(cpf, pageable);
		} else if(nome != null) {
			alunos = alunoService.findByNomeContaining(nome, pageable);
		} else {
			alunos = alunoService.findAll(pageable);			
		}
		
		response = AlunoResponse.converterParaDto(alunos);	
		
		return pagedResourcesAssembler.toModel(response, alunoAssembler);
	}
	
	
	@GetMapping("/{id}")
	public EntityModel<AlunoResponse> getById(@PathVariable("id") Long id){
		
		AlunoResponse response = new AlunoResponse(alunoService.findById(id));
		
		return alunoAssembler.toModel(response);	
	}
	
	
	@GetMapping("/{id}/matriculas")
	public PagedModel<EntityModel<MatriculaResponse>> getMatriculas(@PathVariable("id") Long id, 
			@PageableDefault(sort = "id", direction = Direction.ASC) Pageable pageable){
		
		Page<MatriculaResponse> response = MatriculaResponse.converterParaDto(alunoService.findMatriculas(id, pageable));
		
		return matriculaResourcesAssembler.toModel(response, matriculaAssembler);
	}
	
	
	@GetMapping("/{id}/avaliacoes")
	public PagedModel<EntityModel<AvaliacaoResponse>> getAvaliacoes(@PathVariable("id") Long id, 
			@PageableDefault(sort = "id", direction = Direction.ASC) Pageable pageable){
		
		Page<AvaliacaoResponse> response = AvaliacaoResponse.converterParaDto(alunoService.findAvaliacoes(id, pageable));
		
		return avaliacaoResourcesAssembler.toModel(response, avaliacaoAssembler);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		
		alunoService.deleteByid(id);
		
		return ResponseEntity.ok().build();
	}
	
	
	@PostMapping
	public ResponseEntity<EntityModel<AlunoResponse>> create(@RequestBody @Valid AlunoRequest alunoDto, UriComponentsBuilder uriBuilder){
		
		Aluno aluno = alunoDto.toAluno();
		
		aluno = alunoService.save(aluno);
		
		AlunoResponse response = new AlunoResponse(aluno);
		
		URI uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
		
		return ResponseEntity.created(uri).body(alunoAssembler.toModel(response));
	}
	
	
	@PutMapping("/{id}") @Transactional
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid AlunoRequest alunoDto){
		
		Aluno aluno = alunoService.findById(id);
		
		alunoService.save(alunoDto.atualizar(aluno));
		
		return ResponseEntity.ok().build();
	}
	
}
