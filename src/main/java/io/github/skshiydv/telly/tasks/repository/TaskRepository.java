package io.github.skshiydv.telly.tasks.repository;

import io.github.skshiydv.telly.tasks.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    void findAllByCompleted(boolean status);
}
