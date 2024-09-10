package uz.taskmanagementsystem.taskmanagementsystem.dtos.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.taskmanagementsystem.taskmanagementsystem.enums.TaskStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskDTO {

    private String id;

    private String title;

    private String description;

    private LocalDateTime dueDate;

    private TaskStatus taskStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
