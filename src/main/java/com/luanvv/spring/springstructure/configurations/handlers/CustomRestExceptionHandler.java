package com.luanvv.spring.springstructure.configurations.handlers;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.common.base.Preconditions;
import com.luanvv.spring.springstructure.exeptions.MyResourceNotFoundException;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
@ResponseBody
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (logger.isDebugEnabled()) {
			logger.debug("Missing parameters");
		}
		return super.handleMissingServletRequestParameter(ex, headers, status, request);
	}
	
	@ExceptionHandler(MyResourceNotFoundException.class)
	protected ResponseEntity<Object> handleResourceNotFound(MyResourceNotFoundException ex) {
		if (logger.isDebugEnabled()) {
			logger.debug("Missing parameters");
		}
		return new ResponseEntity<>(error("ResourceNotFound", "Resource not found"), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<VndErrors> handleConstraintViolation(ConstraintViolationException ex) {
		List<String> errors = ex.getConstraintViolations()
				.stream()
				.map(ConstraintViolation::getMessage)
				.collect(Collectors.toList());
		return new ResponseEntity<>(error(ex, errors), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<VndErrors> handleEntityNotfound(EntityNotFoundException ex) {
		return new ResponseEntity<>(error("EntityNotFound", "Entity not found"), HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	    return new ResponseEntity(ex.getBindingResult(), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Error.
	 *
	 * @param logRef the log ref => i18n key
	 * @param error the error => Default message
	 * @return the vnd errors
	 */
	private VndErrors error(String logRef, String error) {
		return new VndErrors(logRef, error);
	}

	private <E extends Exception> VndErrors error(E e, List<String> errors) {
		Preconditions.checkNotNull(errors);
		Preconditions.checkArgument(!errors.isEmpty());
		String msg = e.getClass().getSimpleName();
		return new VndErrors(joinErrorMsgs(errors), msg);
	}

	private String joinErrorMsgs(List<String> errors) {
		return errors.stream().collect(Collectors.joining(""));
	}
	
}
