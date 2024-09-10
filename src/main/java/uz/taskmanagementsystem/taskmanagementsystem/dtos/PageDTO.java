package uz.taskmanagementsystem.taskmanagementsystem.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PageDTO<T> {

    private T content;

    private int totalPage;

    private long totalElements;

    private int currentPage;
}
