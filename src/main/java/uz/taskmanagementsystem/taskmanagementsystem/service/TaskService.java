package uz.taskmanagementsystem.taskmanagementsystem.service;

import uz.taskmanagementsystem.taskmanagementsystem.dtos.task.TaskCreateDTO;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.task.TaskDTO;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.task.TaskUpdateDTO;
import uz.taskmanagementsystem.taskmanagementsystem.entity.Task;

import java.util.List;

public interface TaskService {

    TaskDTO save(TaskCreateDTO taskCreateDTO);

    List<TaskDTO> getAll();

    TaskDTO update(String id, TaskUpdateDTO taskUpdateDTO);

    void delete(String id);

    Task findById(String id);

}
