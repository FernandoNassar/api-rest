package br.com.projeto.apirest.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.projeto.apirest.api.controller.UnidadeController;
import br.com.projeto.apirest.api.dto.unidade.UnidadeResponse;

@Component
public class UnidadeAssembler implements RepresentationModelAssembler<UnidadeResponse, EntityModel<UnidadeResponse>>{

	@Override
	public EntityModel<UnidadeResponse> toModel(UnidadeResponse entity) {
		
		return EntityModel.of(entity, 
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UnidadeController.class).findById(entity.getId())).withSelfRel(),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UnidadeController.class).getCursos(entity.getId(), null)).withRel("cursos"),
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UnidadeController.class).findAll(null, null)).withRel("unidades"));
		
		
		
	}
	
	
	
}
