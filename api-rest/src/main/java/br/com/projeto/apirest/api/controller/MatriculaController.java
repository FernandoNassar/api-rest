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
import br.com.projeto.apirest.api.assembler.CursoAssembler;
import br.com.projeto.apirest.api.assembler.MatriculaAssembler;
import br.com.projeto.apirest.api.dto.aluno.AlunoResponse;
import br.com.projeto.apirest.api.dto.avaliacao.AvaliacaoResponse;
import br.com.projeto.apirest.api.dto.curso.CursoResponse;
import br.com.projeto.apirest.api.dto.matricula.MatriculaRequest;
import br.com.projeto.apirest.api.dto.matricula.MatriculaResponse;
import br.com.projeto.apirest.api.model.Matricula;
import br.com.projeto.apirest.api.services.AlunoService;
import br.com.projeto.apirest.api.services.CursoService;
import br.com.projeto.apirest.api.services.MatriculaService;

@RestController @RequestMapping("/matriculas")
public class MatriculaController {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private MatriculaService matriculaService;
	
	@Autowired
	private MatriculaAssembler matriculaAssembler;
	
	@Autowired
	private AvaliacaoAssembler avaliacaoAssembler;
	
	@Autowired
	private AlunoAssembler alunoAssembler;
	
	@Autowired
	private CursoAssembler cursoAssembler;
	
	@Autowired
	private PagedResourcesAssembler<AvaliacaoResponse> avaliacaoResourcesAssembler;
	
	@Autowired
	private PagedResourcesAssembler<MatriculaResponse> pagedResourcesAssembler;
	
	
	@GetMapping
	public PagedModel<EntityModel<MatriculaResponse>> getAll(@PageableDefault(sort = "id", direction = Direction.ASC) Pageable pageable,
			@RequestParam(required = false) Long alunoID, @RequestParam(required = false) Long cursoID){
		
		Page<Matricula> matriculas;
				
		if(alunoID != null) {
			matriculas = matriculaService.findByAlunoID(alunoID, pageable);
		} else if(cursoID != null) {
			matriculas = matriculaService.findByCursoID(cursoID, pageable);
		} else {
			matriculas = matriculaService.findAll(pageable);
		}
		
		Page<MatriculaResponse> response = MatriculaResponse.converterParaDto(matriculas);

		return pagedResourcesAssembler.toModel(response, matriculaAssembler);
	}
	
	
	@GetMapping("/{id}")
	public EntityModel<MatriculaResponse> getById(@PathVariable("id") Long id){
		
		MatriculaResponse response = new MatriculaResponse(matriculaService.findById(id));
		
		return matriculaAssembler.toModel(response);
	}
	
	
	@GetMapping("/{id}/aluno")
	public EntityModel<AlunoResponse> getAluno(@PathVariable("id") Long id){
		
		AlunoResponse response = new AlunoResponse(matriculaService.findById(id).getAluno());
		
		return alunoAssembler.toModel(response);
	}
	
	
	@GetMapping("/{id}/curso")
	public EntityModel<CursoResponse> getCurso(@PathVariable("id") Long id){
		
		CursoResponse response = new CursoResponse(matriculaService.findById(id).getCurso());
		
		return cursoAssembler.toModel(response);
	}
	
	
	@GetMapping("/{id}/avaliacoes")
	public PagedModel<EntityModel<AvaliacaoResponse>> getAvaliacoes(@PathVariable("id") Long id, 
			@PageableDefault(sort = "id", direction = Direction.ASC) Pageable pageable){
		
		Page<AvaliacaoResponse> response = AvaliacaoResponse.converterParaDto(matriculaService.findAvaliacoes(id, pageable));
		
		return avaliacaoResourcesAssembler.toModel(response, avaliacaoAssembler);
	}
	
	
	@PostMapping
	public ResponseEntity<EntityModel<MatriculaResponse>> create(@RequestBody @Valid MatriculaRequest dto, UriComponentsBuilder uriBuilder){
		
		Matricula matricula = dto.toMatricula(alunoService, cursoService);
		
		matricula = matriculaService.save(matricula);
		
		MatriculaResponse response = new MatriculaResponse(matricula);
		
		URI uri = uriBuilder.path("/matriculas/{id}").buildAndExpand(matricula.getId()).toUri();
		
		return ResponseEntity.created(uri).body(matriculaAssembler.toModel(response));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid MatriculaRequest dto){
		
		Matricula matricula = matriculaService.findById(id);
		
		matriculaService.save(dto.atualizar(matricula, alunoService, cursoService));
		
		return ResponseEntity.ok().build();
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		
		matriculaService.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
	
}
