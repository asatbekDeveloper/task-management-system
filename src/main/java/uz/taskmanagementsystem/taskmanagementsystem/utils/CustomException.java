package uz.taskmanagementsystem.taskmanagementsystem.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomException extends RuntimeException {

    private int code;
    private String message;

    public CustomException(String s, int code) {
        super();
        message = s;
        this.code = code;
    }
}