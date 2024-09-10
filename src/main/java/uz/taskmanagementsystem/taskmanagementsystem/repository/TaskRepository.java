package uz.taskmanagementsystem.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.taskmanagementsystem.taskmanagementsystem.entity.Task;

public interface TaskRepository extends JpaRepository<Task, String> {
}
