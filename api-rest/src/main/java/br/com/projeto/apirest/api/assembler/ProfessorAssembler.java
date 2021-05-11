package br.com.projeto.apirest.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.projeto.apirest.api.controller.ProfessorController;
import br.com.projeto.apirest.api.dto.professor.ProfessorResponse;

@Component
public class ProfessorAssembler implements RepresentationModelAssembler<ProfessorResponse, EntityModel<ProfessorResponse>> {

	@Override
	public EntityModel<ProfessorResponse> toModel(ProfessorResponse entity) {
		return EntityModel.of(entity,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfessorController.class).getById(entity.getId())).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfessorController.class).getCursos(entity.getId(), null)).withRel("Cursos"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProfessorController.class).getAll(null, null, null)).withRel("professores"));
				
	}

}
