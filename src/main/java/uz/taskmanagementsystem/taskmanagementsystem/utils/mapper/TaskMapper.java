package uz.taskmanagementsystem.taskmanagementsystem.utils.mapper;

import org.springframework.stereotype.Component;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.task.TaskCreateDTO;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.task.TaskDTO;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.task.TaskUpdateDTO;
import uz.taskmanagementsystem.taskmanagementsystem.entity.Task;
import uz.taskmanagementsystem.taskmanagementsystem.enums.TaskStatus;

import java.util.List;

@Component
public class TaskMapper {

    public Task fromCreateDTO(TaskCreateDTO taskCreateDTO) {

        return Task.builder()
                .title(taskCreateDTO.getTitle())
                .description(taskCreateDTO.getDescription())
                .dueDate(taskCreateDTO.getDueDate())
                .taskStatus(TaskStatus.OPEN)
                .build();
    }

    public TaskDTO toDTO(Task task) {

        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .taskStatus(task.getTaskStatus())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }

    public List<TaskDTO> toDTOList(List<Task> tasks) {

        return tasks.stream()
                .map(this::toDTO).toList();
    }

    public Task fromUpdateDTO(TaskUpdateDTO taskUpdateDTO, Task task) {

        task.setTitle(taskUpdateDTO.getTitle());
        task.setDescription(taskUpdateDTO.getDescription());
        task.setDueDate(taskUpdateDTO.getDueDate());
        task.setTaskStatus(TaskStatus.valueOf(taskUpdateDTO.getTaskStatus()));

        return task;
    }
}
