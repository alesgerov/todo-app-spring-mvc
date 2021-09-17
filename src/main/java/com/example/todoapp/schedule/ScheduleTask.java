package com.example.todoapp.schedule;

import com.example.todoapp.service.TaskService;
import com.example.todoapp.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ScheduleTask {

    @Autowired
    private UserTokenService service;

    @Autowired
    private TaskService taskService;

    @Scheduled(fixedRate = 5 * 60 * 60000)
    public void deleteExpires() {
        service.deleteExpired();
    }


    @Scheduled(fixedRate = 5 * 60 * 60000)
    public void sendNotification() {
        taskService.sendNotification(LocalDate.now());
    }
}
