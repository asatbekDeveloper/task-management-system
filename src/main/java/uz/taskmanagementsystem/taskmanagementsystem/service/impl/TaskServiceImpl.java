package uz.taskmanagementsystem.taskmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.task.TaskCreateDTO;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.task.TaskDTO;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.task.TaskUpdateDTO;
import uz.taskmanagementsystem.taskmanagementsystem.entity.Task;
import uz.taskmanagementsystem.taskmanagementsystem.repository.TaskRepository;
import uz.taskmanagementsystem.taskmanagementsystem.service.TaskService;
import uz.taskmanagementsystem.taskmanagementsystem.utils.CustomException;
import uz.taskmanagementsystem.taskmanagementsystem.utils.mapper.TaskMapper;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;


    @Override
    public TaskDTO save(TaskCreateDTO taskCreateDTO) {
        log.info("Saving a new task: {}", taskCreateDTO.toString());

        Task task = taskMapper.fromCreateDTO(taskCreateDTO);

        try {
            Task savedTask = taskRepository.save(task);
            log.info("Task saved successfully with id: {}", savedTask.getId());
            return taskMapper.toDTO(savedTask);
        } catch (Exception e) {
            log.error("Internal Server Error Occurred on Task save : {} ", e.getMessage());
            throw new CustomException("Internal Server Error Occurred on Task save", 500);
        }
    }

    @Override
    public List<TaskDTO> getAll() {
        log.info("Fetching all tasks from the database");

        List<Task> tasks = taskRepository.findAll();

        log.info("Found {} tasks in the database", tasks.size());
        return taskMapper.toDTOList(tasks);
    }

    @Override
    public TaskDTO update(String id, TaskUpdateDTO taskUpdateDTO) {
        log.info("Updating task with id: {}", id);

        Task task = findById(id);
        log.debug("Task found for updating: {}", task);

        task = taskMapper.fromUpdateDTO(taskUpdateDTO, task);

        try {
            Task updatedTask = taskRepository.save(task);
            log.info("Task updated successfully with id: {}", updatedTask.getId());
            return taskMapper.toDTO(updatedTask);
        } catch (Exception e) {
            log.error("Internal Server Error Occurred on Task update : {} ", e.getMessage());
            throw new CustomException("Internal Server Error Occurred on Task update", 500);
        }
    }

    @Override
    public void delete(String id) {
        log.info("Deleting task with id: {}", id);

        Task task = findById(id);

        try {
            taskRepository.delete(task);
            log.info("Task deleted successfully with id: {}", id);
        } catch (Exception e) {
            log.error("Internal Server Error Occurred on Task delete : {} ", e.getMessage());
            throw new CustomException("Internal Server Error Occurred on Task delete", 500);
        }
    }

    @Override
    public Task findById(String id) {
        log.info("Finding task by id: {}", id);

        return taskRepository.findById(id).orElseThrow(() -> {
            log.error("Task not found with id {}:", id);
            return new CustomException("Task not found", 404);
        });
    }
}
