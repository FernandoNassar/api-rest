package br.com.projeto.apirest.api.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {
	
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError error = new StandardError();
		error.setTimeStamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Object not found");
		error.setMessage(e.getLocalizedMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<StandardError> noSuchElementException(NoSuchElementException e, HttpServletRequest request){
		
		StandardError error = new StandardError();
		error.setTimeStamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Resource not found");
		error.setMessage(e.getLocalizedMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<StandardError> sqlIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException ex, HttpServletRequest request){
		
		StandardError error = new StandardError();
		error.setTimeStamp(Instant.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("Duplicate Entry");
		error.setMessage(ex.getLocalizedMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<StandardError> emptyResultDataAccessExceptionHandler(EmptyResultDataAccessException e, HttpServletRequest request){
		
		StandardError error = new StandardError();
		error.setTimeStamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Empty result");
		error.setMessage(e.getLocalizedMessage());
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<StandardError> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e, HttpServletRequest request){
		StandardError error = new StandardError();
		error.setTimeStamp(Instant.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("Request method not supported");
		error.setMessage(e.getLocalizedMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
