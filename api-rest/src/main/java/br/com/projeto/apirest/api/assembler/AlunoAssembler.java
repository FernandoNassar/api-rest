package br.com.projeto.apirest.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.projeto.apirest.api.controller.AlunoController;
import br.com.projeto.apirest.api.dto.aluno.AlunoResponse;

@Component
public class AlunoAssembler implements RepresentationModelAssembler<AlunoResponse, EntityModel<AlunoResponse>> {

	@Override
	public EntityModel<AlunoResponse> toModel(AlunoResponse entity) {
		return EntityModel.of(entity,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AlunoController.class).getById(entity.getId())).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AlunoController.class).getMatriculas(entity.getId(), null)).withRel("matriculas"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AlunoController.class).getAvaliacoes(entity.getId(), null)).withRel("avaliações"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AlunoController.class).findAll(null, null, null)).withRel("alunos"));
	}

}
