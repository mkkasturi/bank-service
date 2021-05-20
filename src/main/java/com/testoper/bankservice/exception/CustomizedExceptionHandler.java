package com.testoper.bankservice.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.testoper.bankservice.common.Constants;

/**
 * 
 * 
 * 
 * @author muralikrishnak
 *
 */
@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleAllExceptions(WebRequest request, Exception ex) {
		return sendResponse(request, ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(Constants.VALIDATION_FAILED, errors,
				status.value(), Instant.now().toString(), request.getDescription(false)));
	}

	private ResponseEntity<ErrorResponse> sendResponse(WebRequest request, Exception ex, HttpStatus httpStatus) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		ErrorResponse errorRecord = new ErrorResponse(Constants.FAILED, errors, httpStatus.value(),
				Instant.now().toString(), request.getDescription(false));
		return ResponseEntity.status(httpStatus).body(errorRecord);
	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleNotFoundException(WebRequest request, NotFoundException ex) {
		return sendResponse(request, ex, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InsufficientBalanceException.class)
	public final ResponseEntity<ErrorResponse> handleInsufficientBalanceException(WebRequest request,
			InsufficientBalanceException ex) {
		return sendResponse(request, ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InactiveAccountException.class)
	public final ResponseEntity<ErrorResponse> handleInactiveAccountException(WebRequest request,
			InactiveAccountException ex) {
		return sendResponse(request, ex, HttpStatus.BAD_REQUEST);
	}

}
