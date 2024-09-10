package uz.taskmanagementsystem.taskmanagementsystem.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uz.taskmanagementsystem.taskmanagementsystem.repository.TaskRepository;

@Configuration
@Slf4j
public class DataLoader {

    @Value("${spring.sql.init.mode}")
    private String sqlInitMode;

    private final TaskRepository taskRepository;

    public DataLoader(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Bean
    public CommandLineRunner loadData() {


        return args -> {

            if (sqlInitMode.equals("never")) return;

            try {


            } catch (Exception e) {
                log.error("Exception occurred", e);
            }

        };
    }
}
