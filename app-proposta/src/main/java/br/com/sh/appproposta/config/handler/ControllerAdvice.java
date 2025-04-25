package br.com.sh.appproposta.config.handler;

import br.com.sh.appproposta.model.dto.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Hidden
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handlerRuntimeERROR(RuntimeException e) {
        return ResponseEntity.badRequest().body(ErrorResponseDTO.valueOf(e.getMessage(), HttpStatus.BAD_REQUEST,LocalDateTime.now()));
    }
}
