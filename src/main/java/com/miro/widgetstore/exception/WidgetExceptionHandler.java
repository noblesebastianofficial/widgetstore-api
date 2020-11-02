/**
 * 
 */
package com.miro.widgetstore.exception;

import com.miro.widgetstore.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global Exception handler for Widget Store Service
 *
 */
@RestControllerAdvice
public class WidgetExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { WidgetNotFoundException.class })
	protected ResponseEntity<Object> handleConflict(WidgetNotFoundException ex, WebRequest request) {
		ErrorResponseDTO response = new ErrorResponseDTO();
		response.setMessage(ex.getLocalizedMessage());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(value = { WidgetValidationException.class })
	protected ResponseEntity<Object> handleValidation(WidgetValidationException ex, WebRequest request) {
		ErrorResponseDTO response = new ErrorResponseDTO();
		response.setMessage(ex.getLocalizedMessage());
		response.setStatus(HttpStatus.PRECONDITION_FAILED.value());
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
	}

}
