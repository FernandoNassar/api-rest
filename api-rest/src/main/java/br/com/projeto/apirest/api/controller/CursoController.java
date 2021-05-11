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
import br.com.projeto.apirest.api.assembler.MatriculaAssembler;
import br.com.projeto.apirest.api.assembler.ProfessorAssembler;
import br.com.projeto.apirest.api.assembler.UnidadeAssembler;
import br.com.projeto.apirest.api.dto.curso.CursoResponse;
import br.com.projeto.apirest.api.dto.matricula.MatriculaResponse;
import br.com.projeto.apirest.api.dto.professor.ProfessorResponse;
import br.com.projeto.apirest.api.dto.unidade.UnidadeResponse;
import br.com.projeto.apirest.api.dto.curso.CursoRequest;
import br.com.projeto.apirest.api.model.Curso;
import br.com.projeto.apirest.api.services.CursoService;
import br.com.projeto.apirest.api.services.ProfessorService;
import br.com.projeto.apirest.api.services.UnidadeService;

@RestController @RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private UnidadeService unidadeService;
	
	@Autowired
	private CursoAssembler cursoAssembler;
	
	@Autowired
	private UnidadeAssembler unidadeAssembler;
	
	@Autowired
	private ProfessorAssembler professorAssembler;
	
	@Autowired
	private MatriculaAssembler matriculaAssembler;
	
	@Autowired
	private PagedResourcesAssembler<MatriculaResponse> matriculaResourcesAssembler;
	
	@Autowired
	private PagedResourcesAssembler<CursoResponse> pagedResourcesAssembler;
	
	
	@GetMapping
	public PagedModel<EntityModel<CursoResponse>> getAll(@PageableDefault(sort = "id", direction = Direction.ASC) Pageable pageable,
			@RequestParam(required = false) String nome, @RequestParam(required = false) Long professorID){
		
		Page<Curso> cursos;
		
		if(nome != null) {
			cursos = cursoService.findByNomeContaining(nome, pageable);
		} else if(professorID != null) {
			cursos = cursoService.findByProfessorID(professorID, pageable);
		} else {
			cursos = cursoService.findAll(pageable);
		}
		
		Page<CursoResponse> response = CursoResponse.toDto(cursos);
		
		return pagedResourcesAssembler.toModel(response, cursoAssembler);
	}
	
	
	@GetMapping("/{id}")
	public EntityModel<CursoResponse> getById(@PathVariable("id") Long id) {
		
		CursoResponse response = new CursoResponse(cursoService.findById(id));
		
		return cursoAssembler.toModel(response);
	}
	
	
	@GetMapping("/{id}/unidade")
	public EntityModel<UnidadeResponse> getUnidade(@PathVariable("id") Long id){
		
		UnidadeResponse response = new UnidadeResponse(cursoService.findById(id).getUnidade());
		
		return unidadeAssembler.toModel(response);
	}
	
	
	@GetMapping("/{id}/professor")
	public EntityModel<ProfessorResponse> getProfessor(@PathVariable("id") Long id){
		
		ProfessorResponse response = new ProfessorResponse(cursoService.findById(id).getProfessor());
		
		return professorAssembler.toModel(response);
	}
	
	
	@GetMapping("/{id}/matriculas")
	public PagedModel<EntityModel<MatriculaResponse>> getMatriculas(@PathVariable("id") Long id, 
			@PageableDefault(sort = "id", direction = Direction.ASC) Pageable pageable){
		
		Page<MatriculaResponse> response = MatriculaResponse.converterParaDto(cursoService.findMatriculas(id, pageable));
		
		return matriculaResourcesAssembler.toModel(response, matriculaAssembler);
	}
	
	
	@PostMapping
	public ResponseEntity<EntityModel<CursoResponse>> create(@RequestBody @Valid CursoRequest dto, UriComponentsBuilder uriBuilder){
		
		Curso curso = dto.toCurso(professorService, unidadeService);
		
		curso = cursoService.save(curso);
		
		CursoResponse response = new CursoResponse(curso);
		
		URI uri = uriBuilder.path("cursos/{id}").buildAndExpand(curso.getId()).toUri();
		
		return ResponseEntity.created(uri).body(cursoAssembler.toModel(response));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid CursoRequest dto){
		
		Curso curso = cursoService.findById(id);
		
		curso = dto.atualizar(curso, professorService, unidadeService);
		
		cursoService.save(curso);
		
		return ResponseEntity.ok().build();
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		
		cursoService.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
}















