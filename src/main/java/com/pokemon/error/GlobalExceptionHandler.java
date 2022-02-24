package com.pokemon.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.pokemon.controller.UsuarioController;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// Logger for information
	Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@ExceptionHandler(NoUniqueNamesException.class)
	public ResponseEntity<ErrorDetails> handleResourceNoUniqueNamesException(NoUniqueNamesException exception, 
			WebRequest webrequest){
		ErrorDetails error = new ErrorDetails(exception.getMessage(), webrequest.getDescription(false));
	
		error(error);
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(IncorrectResultSizeDataAccessException.class)
	public ResponseEntity<ErrorDetails> handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException exception, 
			WebRequest webrequest){
		ErrorDetails error = new ErrorDetails("Pokemon already exist", webrequest.getDescription(false));
	
		error(error);
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> handleResourceMethodArgumentNotValidException(MethodArgumentNotValidException exception, 
			WebRequest webrequest){
		ErrorDetails error = null;
		String message = "";
		if(exception.getMessage().contains("unique")) {
			message = " You can only add one pokemon per name. ";
		}else {
			message = " You must select unless a pokemon and it's type.";
		}
		error = new ErrorDetails(message, webrequest.getDescription(false));
		
		error(error);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private void error(ErrorDetails error) {
		log.error(" A error has ocurred: " + error.getMessage() + " on " + error.getDetails());
	}

}
