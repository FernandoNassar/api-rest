package br.com.projeto.apirest.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.projeto.apirest.api.controller.MatriculaController;
import br.com.projeto.apirest.api.dto.matricula.MatriculaResponse;

@Component
public class MatriculaAssembler implements RepresentationModelAssembler<MatriculaResponse, EntityModel<MatriculaResponse>> {

	@Override
	public EntityModel<MatriculaResponse> toModel(MatriculaResponse entity) {
		return EntityModel.of(entity, 
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MatriculaController.class).getById(entity.getId())).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MatriculaController.class).getAluno(entity.getId())).withRel("aluno"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MatriculaController.class).getCurso(entity.getId())).withRel("curso"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MatriculaController.class).getAvaliacoes(entity.getId(), null)).withRel("avaliações"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MatriculaController.class).getAll(null, null, null)).withRel("matriculas"));
	}

}
