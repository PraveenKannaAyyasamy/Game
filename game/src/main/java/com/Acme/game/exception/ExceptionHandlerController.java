package com.Acme.game.exception;

import com.Acme.game.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerController {



    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException ex)
    {
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(ex.getLocalizedMessage())
                .timeStamp(LocalDateTime.now().toString()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> handleFileNotFound(FileNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error: File not found. Please upload a valid CSV file.");
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: Unable to process the file. Please try again.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error: Invalid CSV format. Please ensure all required columns exist.");
    }



}
