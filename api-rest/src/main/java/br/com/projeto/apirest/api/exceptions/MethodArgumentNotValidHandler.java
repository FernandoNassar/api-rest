package br.com.projeto.apirest.api.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MethodArgumentNotValidHandler {
	
	@Autowired
	MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroValidacao> methodArgumentNotValid(MethodArgumentNotValidException exc){
		
		List<ErroValidacao> erroDto = new ArrayList<>();
		List<FieldError> fieldErrors = exc.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach( e ->{ 
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroValidacao erro = new ErroValidacao(e.getField(), message);
			erroDto.add(erro);
		});
		
		return erroDto;
		
	}
	
	
	
}	
