package com.server.cms.config.handler;

import com.server.cms.config.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
//@ApiIgnore
@ControllerAdvice
public class GeneralExceptionHandler {

    private ResponseEntity<ApiResult> newResponse(Throwable throwable, HttpStatus status) {
        HttpHeaders httpStatus = new HttpHeaders();
        httpStatus.add("Content-Type", "application/json");
        return new ResponseEntity<>(ApiResult.ERROR(throwable, status),httpStatus, status);
    }

    private ResponseEntity<ApiResult> newResponse(String message, HttpStatus status) {
        HttpHeaders httpStatus = new HttpHeaders();
        httpStatus.add("Content-Type", "application/json");
        return new ResponseEntity<>(ApiResult.ERROR(message, status),httpStatus, status);
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<?> handleBindException(BindException e) {
        log.debug("Bad request exception occurred: {}", e.getMessage(), e);
        return newResponse(e.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNotFoundException(Exception e) {
        return newResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            IllegalStateException.class, IllegalArgumentException.class,
            TypeMismatchException.class, HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class, MultipartException.class
    })
    public ResponseEntity<?> handleBadRequestException(Exception e) {
        log.debug("Bad request exception occurred: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleUnexpectedTypeException(MethodArgumentNotValidException e) {
        log.info("Bad request exception occurred: {}", e.getFieldError().getDefaultMessage());
        return newResponse(e.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMediaTypeException.class)
    public ResponseEntity<?> handleHttpMediaTypeException(Exception e) {
        return newResponse(e, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleMethodNotAllowedException(Exception e) {
        return newResponse(e, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<?> handleException(Exception e) {
        log.error("Unexpected exception occurred: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
