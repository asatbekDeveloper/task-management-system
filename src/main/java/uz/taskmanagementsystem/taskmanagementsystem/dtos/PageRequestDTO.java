package uz.taskmanagementsystem.taskmanagementsystem.dtos;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PageRequestDTO {

    private int page;

    private int size;
}
