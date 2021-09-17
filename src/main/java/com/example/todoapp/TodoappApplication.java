package com.example.todoapp;

import com.example.todoapp.repository.TokenRepository;
import com.example.todoapp.schedule.ScheduleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledFuture;

@EnableScheduling
@SpringBootApplication
public class TodoappApplication {


    public static void main(String[] args) {
        SpringApplication.run(TodoappApplication.class, args);
    }

}
