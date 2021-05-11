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

import br.com.projeto.apirest.api.assembler.CursoAssembler;
import br.com.projeto.apirest.api.assembler.UnidadeAssembler;
import br.com.projeto.apirest.api.dto.curso.CursoResponse;
import br.com.projeto.apirest.api.dto.unidade.UnidadeRequest;
import br.com.projeto.apirest.api.dto.unidade.UnidadeResponse;
import br.com.projeto.apirest.api.model.Curso;
import br.com.projeto.apirest.api.model.Unidade;
import br.com.projeto.apirest.api.services.UnidadeService;


@RestController @RequestMapping("/unidades")
public class UnidadeController {
	
	@Autowired
	private UnidadeService unidadeService;
	
	@Autowired
	private UnidadeAssembler unidadeAssembler;
	
	@Autowired
	private CursoAssembler cursoAssembler;
	
	@Autowired
	private PagedResourcesAssembler<UnidadeResponse> pagedResourcesAssembler;
	
	@Autowired
	private PagedResourcesAssembler<CursoResponse> cursoResourcesAssembler;
	
	
	@GetMapping
	public PagedModel<EntityModel<UnidadeResponse>> findAll(
			@PageableDefault(sort = "id", direction = Direction.ASC) Pageable pageable, 
			@RequestParam(required = false) String nome){
		// ?page=0&size=10&sort=id,desc
		
		Page<UnidadeResponse> response;
		
		Page<Unidade> unidades;
		
		if(nome != null) {
			unidades = unidadeService.findByNomeContaining(nome, pageable);
		} else {
			unidades = unidadeService.findAll(pageable);
		}
		
		response = UnidadeResponse.converterParaDto(unidades);
		
		return pagedResourcesAssembler.toModel(response, unidadeAssembler);		
	}
	
	
	@GetMapping("/{id}")
	public EntityModel<UnidadeResponse> findById(@PathVariable("id") Long id){
		
		UnidadeResponse response = new UnidadeResponse(unidadeService.findById(id));
		
		return unidadeAssembler.toModel(response);
	}
	
	
	@GetMapping("/{id}/cursos")
	public PagedModel<EntityModel<CursoResponse>> getCursos(@PathVariable("id") Long id, @PageableDefault(sort = "id", direction = Direction.ASC) Pageable pageable){
		
		Page<Curso> cursos = unidadeService.findCursos(id, pageable);
		
		Page<CursoResponse> response = CursoResponse.toDto(cursos); 
		
		return cursoResourcesAssembler.toModel(response, cursoAssembler);
	}
	 
	
	@PostMapping
	public ResponseEntity<EntityModel<UnidadeResponse>> create(@Valid @RequestBody UnidadeRequest dto, UriComponentsBuilder uriBuilder) {
		
		Unidade unidade = dto.toUnidade();
		
		unidade = unidadeService.save(unidade);
		
		UnidadeResponse response = new UnidadeResponse(unidade);
		
		URI uri = uriBuilder.path("/unidades/{id}").buildAndExpand(unidade.getId()).toUri();
		
		return ResponseEntity.created(uri).body(unidadeAssembler.toModel(response));
	
	}
	
	
	@PutMapping("/{id}") @Transactional
	public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody UnidadeRequest dto){
		
		Unidade unidade = unidadeService.findById(id);
		
		dto.atualizar(unidade);
		
		return ResponseEntity.ok().build();
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Long id){
		
		unidadeService.deleteById(id);
		
		return ResponseEntity.ok().build();
		
	}
	

	
}

























