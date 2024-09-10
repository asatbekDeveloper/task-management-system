package uz.taskmanagementsystem.taskmanagementsystem.dtos.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskUpdateDTO {

    private String title;

    private String description;

    private LocalDateTime dueDate;

    private String taskStatus;
}
