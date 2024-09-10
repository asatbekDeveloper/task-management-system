package uz.taskmanagementsystem.taskmanagementsystem.dtos.task;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskCreateDTO {

    @NotBlank(message = "title required")
    private String title;

    private String description;

    private LocalDateTime dueDate;
}
