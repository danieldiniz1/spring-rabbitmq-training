package br.com.sh.appproposta.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {

    private String error, ErrorCode, timestamp;

    public static ErrorResponseDTO valueOf(String message, HttpStatus badRequest, LocalDateTime now) {
        return new ErrorResponseDTO(message, badRequest.toString(), now.toString());
    }
}
