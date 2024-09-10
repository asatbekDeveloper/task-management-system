package uz.taskmanagementsystem.taskmanagementsystem.utils;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ResponseDTO<String>> customException(CustomException customException) {

        log.error("CustomException occurred: Code {}, Message {}", customException.getCode(), customException.getMessage());

        return new ResponseEntity<>(new ResponseDTO<>(customException.getMessage(), "error", customException.getCode()), HttpStatusCode.valueOf(customException.getCode()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseDTO<String>> exception(Exception exception) {

        log.error("Exception occurred: Message {}", exception.getMessage());

        return new ResponseEntity<>(new ResponseDTO<>(exception.getMessage(), "error", 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ResponseDTO<String>> runtimeException(RuntimeException runtimeException) {

        log.error("RuntimeException occurred: Message {}", runtimeException.getMessage());

        return new ResponseEntity<>(new ResponseDTO<>(runtimeException.getMessage(), "error", 400), HttpStatus.BAD_REQUEST);
    }

}