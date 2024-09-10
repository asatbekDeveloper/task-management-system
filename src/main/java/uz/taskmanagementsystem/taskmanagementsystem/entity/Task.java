package uz.taskmanagementsystem.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.taskmanagementsystem.taskmanagementsystem.entity.base.BaseEntity;
import uz.taskmanagementsystem.taskmanagementsystem.enums.TaskStatus;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tasks")
@Entity
@Builder
public class Task extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    private String description;

    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
}
