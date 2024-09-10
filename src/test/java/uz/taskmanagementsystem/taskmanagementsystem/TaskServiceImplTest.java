package uz.taskmanagementsystem.taskmanagementsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.task.TaskCreateDTO;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.task.TaskDTO;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.task.TaskUpdateDTO;
import uz.taskmanagementsystem.taskmanagementsystem.entity.Task;
import uz.taskmanagementsystem.taskmanagementsystem.enums.TaskStatus;
import uz.taskmanagementsystem.taskmanagementsystem.repository.TaskRepository;
import uz.taskmanagementsystem.taskmanagementsystem.service.TaskService;
import uz.taskmanagementsystem.taskmanagementsystem.service.impl.TaskServiceImpl;
import uz.taskmanagementsystem.taskmanagementsystem.utils.CustomException;
import uz.taskmanagementsystem.taskmanagementsystem.utils.mapper.TaskMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;
    private TaskDTO taskDTO;
    private TaskCreateDTO taskCreateDTO;
    private TaskUpdateDTO taskUpdateDTO;

    @BeforeEach
    void setUp() {
        task = new Task();// Create a task object for mocking
        task.setId("f09c9c62-ef68-44cd-b698-873a324cf710");
        task.setTitle("Test Task Title");
        task.setDescription("Test Task Description");
        task.setDueDate(LocalDateTime.now());
        task.setTaskStatus(TaskStatus.OPEN);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        taskDTO = new TaskDTO();  // Create a task DTO object for testing
        taskDTO.setId("f09c9c62-ef68-44cd-b698-873a324cf710");
        taskDTO.setTitle("Test Task Title");
        taskDTO.setDescription("Test Task Description");
        taskDTO.setDueDate(LocalDateTime.now());
        taskDTO.setTaskStatus(TaskStatus.OPEN);
        taskDTO.setCreatedAt(LocalDateTime.now());
        taskDTO.setUpdatedAt(LocalDateTime.now());

        taskCreateDTO = new TaskCreateDTO();
        taskCreateDTO.setTitle("Test Create Task Title");
        taskCreateDTO.setDescription("Test Create Task Description");
        taskCreateDTO.setDueDate(LocalDateTime.now());

        taskUpdateDTO = new TaskUpdateDTO();
        taskUpdateDTO.setTitle("Test Update Task Title");
        taskUpdateDTO.setDescription("Test Update Task Description");
        taskUpdateDTO.setDueDate(LocalDateTime.now());
        taskUpdateDTO.setTaskStatus(TaskStatus.IN_PROGRESS.name());
    }

    @Test
    void save_ShouldReturnSavedTaskDTO() {
        // Arrange
        when(taskMapper.fromCreateDTO(any(TaskCreateDTO.class))).thenReturn(task);
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(taskMapper.toDTO(any(Task.class))).thenReturn(taskDTO);

        // Act
        TaskDTO result = taskService.save(taskCreateDTO);

        // Assert
        assertNotNull(result);
        assertEquals(taskDTO.getId(), result.getId());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void findById_ShouldReturnTask() {
        // Arrange
        when(taskRepository.findById(anyString())).thenReturn(Optional.of(task));

        // Act
        Task result = taskService.findById("f09c9c62-ef68-44cd-b698-873a324cf710");

        // Assert
        assertNotNull(result);
        assertEquals("f09c9c62-ef68-44cd-b698-873a324cf710", result.getId());
        verify(taskRepository, times(1)).findById("f09c9c62-ef68-44cd-b698-873a324cf710");
    }

    @Test
    void findById_ShouldThrowException_WhenTaskNotFound() {
        // Arrange
        when(taskRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        CustomException exception = assertThrows(CustomException.class, () -> taskService.findById("f09c9c62-ef68-44cd-b698-873a324cf710"));
        assertEquals("Task not found", exception.getMessage());
        verify(taskRepository, times(1)).findById("f09c9c62-ef68-44cd-b698-873a324cf710");
    }

    @Test
    void getAll_ShouldReturnListOfTasks() {
        // Arrange
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task));
        when(taskMapper.toDTOList(anyList())).thenReturn(Arrays.asList(taskDTO));

        // Act
        List<TaskDTO> result = taskService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void update_ShouldReturnUpdatedTaskDTO() {
        // Arrange
        when(taskRepository.findById(anyString())).thenReturn(Optional.of(task));
        when(taskMapper.fromUpdateDTO(any(TaskUpdateDTO.class), any(Task.class))).thenReturn(task);
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(taskMapper.toDTO(any(Task.class))).thenReturn(taskDTO);

        // Act
        TaskDTO result = taskService.update("f09c9c62-ef68-44cd-b698-873a324cf710", taskUpdateDTO);

        // Assert
        assertNotNull(result);
        assertEquals(taskDTO.getId(), result.getId());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void delete_ShouldDeleteTask() {
        // Arrange
        when(taskRepository.findById(anyString())).thenReturn(Optional.of(task));
        doNothing().when(taskRepository).delete(any(Task.class));

        // Act
        taskService.delete("f09c9c62-ef68-44cd-b698-873a324cf710");

        // Assert
        verify(taskRepository, times(1)).delete(task);
    }

}
