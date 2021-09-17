package com.example.todoapp.form;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TaskForm {
    private String title;
    private String description;
    private boolean isDone;
    private boolean needNotification;
    private LocalDateTime createdTime;
    private LocalDate validDate;
}
