package br.com.projeto.apirest.api.controller;

import java.net.URI;

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

import br.com.projeto.apirest.api.assembler.CursoAssembler;
import br.com.projeto.apirest.api.assembler.ProfessorAssembler;
import br.com.projeto.apirest.api.dto.professor.ProfessorResponse;
import br.com.projeto.apirest.api.dto.curso.CursoResponse;
import br.com.projeto.apirest.api.dto.professor.ProfessorRequest;
import br.com.projeto.apirest.api.model.Professor;
import br.com.projeto.apirest.api.services.ProfessorService;

@RestController @RequestMapping("/professores")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private ProfessorAssembler professorAssembler;
	
	@Autowired
	private CursoAssembler cursoAssembler;
	
	@Autowired
	private PagedResourcesAssembler<CursoResponse> cursoResourcesAssembler;
	
	@Autowired
	private PagedResourcesAssembler<ProfessorResponse> pagedResourcesAssembler;
	
	
	@GetMapping
	public PagedModel<EntityModel<ProfessorResponse>> getAll(@PageableDefault(sort = "id", direction = Direction.ASC) Pageable pageable,
			@RequestParam(required = false) String cpf, @RequestParam(required = false) String nome){
		
		Page<Professor> professores;
		
		Page<ProfessorResponse> response;
		
		if(cpf != null) {
			professores = professorService.findByCpf(cpf, pageable);
		} else if(nome != null) {
			professores = professorService.findByNomeContaining(nome, pageable);
		} else {
			professores = professorService.findAll(pageable);
		}
		
		response = ProfessorResponse.toDto(professores);
		
		return pagedResourcesAssembler.toModel(response, professorAssembler);
	}
	
	
	@GetMapping("/{id}")
	public EntityModel<ProfessorResponse> getById(@PathVariable("id") Long id){
		
		ProfessorResponse response = new ProfessorResponse(professorService.findById(id));
		
		return professorAssembler.toModel(response);
	}
	
	
	@GetMapping("/{id}/cursos")
	public PagedModel<EntityModel<CursoResponse>> getCursos(@PathVariable("id") Long id, 
			@PageableDefault(sort = "id", direction = Direction.ASC) Pageable pageable){
		
		Page<CursoResponse> response = CursoResponse.toDto(professorService.findCursos(id, pageable));
		
		return cursoResourcesAssembler.toModel(response, cursoAssembler);
	}
	
	
	@PostMapping
	public ResponseEntity<EntityModel<ProfessorResponse>> create(@RequestBody @Valid ProfessorRequest professorDto, UriComponentsBuilder uriBuilder){
		
		Professor professor = professorDto.toProfessor();
		
		professor = professorService.save(professor);
		
		ProfessorResponse response = new ProfessorResponse(professor);
		
		URI uri = uriBuilder.path("/professores/{id}").buildAndExpand(professor.getId()).toUri();
		
		return ResponseEntity.created(uri).body(professorAssembler.toModel(response));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody @Valid ProfessorRequest professorDto, @PathVariable("id") Long id){
		
		Professor professor = professorService.findById(id);
		
		professorService.save(professorDto.atualizar(professor));
		
		return ResponseEntity.ok().build();
	}
	
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		
		professorService.deleteById(id);
	}
	
}
