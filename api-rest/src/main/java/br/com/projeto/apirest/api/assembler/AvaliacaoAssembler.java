package br.com.projeto.apirest.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.projeto.apirest.api.controller.AvaliacaoController;
import br.com.projeto.apirest.api.dto.avaliacao.AvaliacaoResponse;

@Component
public class AvaliacaoAssembler implements RepresentationModelAssembler<AvaliacaoResponse, EntityModel<AvaliacaoResponse>> {

	@Override
	public EntityModel<AvaliacaoResponse> toModel(AvaliacaoResponse entity) {
		return EntityModel.of(entity, 
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AvaliacaoController.class).getById(entity.getId())).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AvaliacaoController.class).getAluno(entity.getId())).withRel("aluno"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AvaliacaoController.class).getMatricula(entity.getId())).withRel("matricula"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AvaliacaoController.class).getAll(null, null, null)).withRel("avaliacoes"));
	}

}
