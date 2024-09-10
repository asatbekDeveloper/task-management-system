package uz.taskmanagementsystem.taskmanagementsystem.dtos;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseDTO<T> {

    private T data;

    private String message;

    private int code;

    private String messageDetail;

    public ResponseDTO(T data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
}