package dev.practice.lf.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ItemAPIException extends RuntimeException {
    private HttpStatus status;
    private String message;
}
