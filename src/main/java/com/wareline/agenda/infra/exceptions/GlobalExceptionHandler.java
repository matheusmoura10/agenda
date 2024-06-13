package com.wareline.agenda.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.wareline.agenda.shared.validation.Error;

import com.wareline.agenda.shared.validation.handler.Notification;

import io.vavr.collection.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Notification> handleValidationException(MethodArgumentNotValidException ex) {

        Notification notification = Notification.create();

        List<FieldError> fieldErrors = List.ofAll(ex.getBindingResult().getFieldErrors());

        fieldErrors.zipWithIndex().forEach(tuple -> {
            FieldError fieldError = tuple._1;
            int index = tuple._2;
            String field = formatIndexedField(fieldError.getField(),index);
            String message = fieldError.getDefaultMessage();

            notification.append(new Error(field,message));
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(notification);
    }

    private String formatIndexedField(String field, int index) {


        return field;
    }
}