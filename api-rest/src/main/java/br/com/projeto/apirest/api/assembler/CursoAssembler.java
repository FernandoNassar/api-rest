package br.com.projeto.apirest.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.projeto.apirest.api.controller.CursoController;
import br.com.projeto.apirest.api.dto.curso.CursoResponse;

@Component
public class CursoAssembler implements RepresentationModelAssembler<CursoResponse, EntityModel<CursoResponse>> {

	@Override
	public EntityModel<CursoResponse> toModel(CursoResponse entity) {
		return EntityModel.of(entity, 
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class).getById(entity.getId())).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class).getUnidade(entity.getId())).withRel("unidade"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class).getProfessor(entity.getId())).withRel("professor"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class).getMatriculas(entity.getId(), null)).withRel("matriculas"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CursoController.class).getAll(null, null, null)).withRel("cursos"));
	}

}
