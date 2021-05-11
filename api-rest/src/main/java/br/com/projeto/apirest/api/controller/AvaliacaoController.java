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

import br.com.projeto.apirest.api.assembler.AlunoAssembler;
import br.com.projeto.apirest.api.assembler.AvaliacaoAssembler;
import br.com.projeto.apirest.api.assembler.MatriculaAssembler;
import br.com.projeto.apirest.api.dto.avaliacao.AvaliacaoResponse;
import br.com.projeto.apirest.api.dto.matricula.MatriculaResponse;
import br.com.projeto.apirest.api.dto.aluno.AlunoResponse;
import br.com.projeto.apirest.api.dto.avaliacao.AvaliacaoRequest;
import br.com.projeto.apirest.api.model.Avaliacao;
import br.com.projeto.apirest.api.services.AvaliacaoService;
import br.com.projeto.apirest.api.services.MatriculaService;

@RestController @RequestMapping("/avaliacoes")
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@Autowired
	private MatriculaService matriculaService;
	
	@Autowired
	private AvaliacaoAssembler avaliacaoAssembler;
	
	@Autowired
	private AlunoAssembler alunoAssembler;
	
	@Autowired
	private MatriculaAssembler matriculaAssembler;
	
	@Autowired
	private PagedResourcesAssembler<AvaliacaoResponse> pagedResourcesAssembler;
	
	
	@GetMapping
	public PagedModel<EntityModel<AvaliacaoResponse>> getAll(@PageableDefault(sort = "id", direction = Direction.ASC) Pageable pageable,
			@RequestParam(required = false) Long alunoID, @RequestParam(required = false) Long cursoID){
		
		Page<Avaliacao> avaliacoes;			
		
		if(alunoID != null) {
			avaliacoes = avaliacaoService.findByAlunoID(alunoID, pageable);
		} else if(cursoID != null){
			avaliacoes = avaliacaoService.findByCursoID(cursoID, pageable);
		} else {
			avaliacoes = avaliacaoService.findAll(pageable);
		}
		
		Page<AvaliacaoResponse> response = AvaliacaoResponse.converterParaDto(avaliacoes);
		
		return pagedResourcesAssembler.toModel(response, avaliacaoAssembler);	
	}
	
	
	@GetMapping("/{id}")
	public EntityModel<AvaliacaoResponse> getById(@PathVariable("id") Long id){
		
		AvaliacaoResponse response = new AvaliacaoResponse(avaliacaoService.findById(id));
		
		return avaliacaoAssembler.toModel(response);
	}
	
	
	@GetMapping("/{id}/aluno")
	public EntityModel<AlunoResponse> getAluno(@PathVariable("id") Long id){
		
		AlunoResponse response = new AlunoResponse(avaliacaoService.findById(id).getMatricula().getAluno());
		
		return alunoAssembler.toModel(response);
	}
	
	
	@GetMapping("/{id}/matricula")
	public EntityModel<MatriculaResponse> getMatricula(@PathVariable("id") Long id){
		
		MatriculaResponse response = new MatriculaResponse(avaliacaoService.findById(id).getMatricula());
		
		return matriculaAssembler.toModel(response);
	}
	
	
	@PostMapping
	public ResponseEntity<EntityModel<AvaliacaoResponse>> create(@RequestBody @Valid AvaliacaoRequest dto, UriComponentsBuilder uriBuilder){
		
		Avaliacao avaliacao = dto.toAvaliacao(matriculaService);
		
		avaliacao = avaliacaoService.save(avaliacao);
		
		AvaliacaoResponse response = new AvaliacaoResponse(avaliacao);
		
		URI uri = uriBuilder.path("/avaliacoes/{id}").buildAndExpand(avaliacao.getId()).toUri();
		
		return ResponseEntity.created(uri).body(avaliacaoAssembler.toModel(response));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody @Valid AvaliacaoRequest dto, @PathVariable("id") Long id) {
		
		Avaliacao avaliacao = avaliacaoService.findById(id);
		
		avaliacao = dto.atualizar(avaliacao, matriculaService);
		
		avaliacaoService.save(avaliacao);
		
		return ResponseEntity.ok().build();
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		
		avaliacaoService.deleteById(id);
		
		return ResponseEntity.ok().build();
	}

	
	
}
