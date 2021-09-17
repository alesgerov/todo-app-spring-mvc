package com.example.todoapp.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Transactional
@Setter
public class TaskModel {
    @Id
    @SequenceGenerator(
            name = "task_id_seq",
            sequenceName = "task_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_id_seq"
    )
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private boolean isDone=false;
    private boolean needNotification=false;
    private LocalDateTime createdTime;
    private LocalDate validDate;
}
