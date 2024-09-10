package uz.taskmanagementsystem.taskmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.ResponseDTO;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.task.TaskCreateDTO;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.task.TaskDTO;
import uz.taskmanagementsystem.taskmanagementsystem.dtos.task.TaskUpdateDTO;
import uz.taskmanagementsystem.taskmanagementsystem.service.TaskService;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin("*")
@Tag(name = "1. Task Controller", description = "Task Related APIs")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "API ID: TMTask001")
    public ResponseEntity<ResponseDTO<TaskDTO>> save(@Valid @RequestBody TaskCreateDTO taskCreateDTO) {

        TaskDTO taskDTO = taskService.save(taskCreateDTO);
        return new ResponseEntity<>(new ResponseDTO<>(taskDTO, "success", OK.value(), "Successfully Saved"), OK);
    }


    @GetMapping
    @Operation(summary = "API ID: TMTask002")
    public ResponseEntity<ResponseDTO<List<TaskDTO>>> getAll() {

        List<TaskDTO> taskDTOList = taskService.getAll();
        return new ResponseEntity<>(new ResponseDTO<>(taskDTOList, "success", OK.value()), OK);
    }


    @PutMapping("/{id}")
    @Operation(summary = "API ID: TMTask003")
    public ResponseEntity<ResponseDTO<TaskDTO>> update(@PathVariable String id, @Valid @RequestBody TaskUpdateDTO taskUpdateDTO) {

        TaskDTO taskDTO = taskService.update(id,taskUpdateDTO);
        return new ResponseEntity<>(new ResponseDTO<>(taskDTO, "success", OK.value(), "Successfully Updated"), OK);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "API ID: TMTask004")
    public ResponseEntity<ResponseDTO<Void>> delete(@PathVariable String id) {

        taskService.delete(id);
        return new ResponseEntity<>(null, NO_CONTENT);
    }
}
