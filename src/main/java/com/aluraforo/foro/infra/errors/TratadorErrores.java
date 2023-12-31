package com.aluraforo.foro.infra.errors;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

//Actua como proxy para todos los controladores, esta atento a si hay excepciones
@RestControllerAdvice
public class TratadorErrores {
   
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<HttpStatus> tratarError404 () {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<List<DatosErrorValidacion>> tratarError400 (MethodArgumentNotValidException e) {
        var errores  =  e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();

        return ResponseEntity.badRequest().body(errores);
    }
    
    @ExceptionHandler(Validacion.class)
    public ResponseEntity<Object> tratarErrorDuplicado (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    private record DatosErrorValidacion(String campo,String error) {
        public DatosErrorValidacion (FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
 