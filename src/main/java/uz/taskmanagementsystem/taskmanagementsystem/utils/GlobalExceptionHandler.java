package uz.taskmanagementsystem.taskmanagementsystem.utils;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.ResponseDTO;

@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ResponseDTO<String>> customException(CustomException customException) {
        return new ResponseEntity<>(new ResponseDTO<>(customException.getMessage(), "error", customException.getCode()), HttpStatusCode.valueOf(customException.getCode()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseDTO<String>> exception(Exception exception) {
        return new ResponseEntity<>(new ResponseDTO<>(exception.getMessage(), "error", 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ResponseDTO<String>> runtimeException(RuntimeException runtimeException) {
        return new ResponseEntity<>(new ResponseDTO<>(runtimeException.getMessage(), "error", 400), HttpStatus.BAD_REQUEST);
    }

}