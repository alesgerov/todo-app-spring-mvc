package com.example.todoapp.repository;

import com.example.todoapp.model.ClassForNotification;
import com.example.todoapp.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface NotificationRepo extends JpaRepository<TaskModel,Long> {
    @Query(
            "select new com.example.todoapp.model.ClassForNotification(tuser.email,task.title) from TaskModel task join TodoUser tuser on task.userId=tuser.id " +
                    "where task.validDate<= :date and task.needNotification=true and task.isDone=false "
    )
    List<ClassForNotification> findEmailsForSending(@Param("date") LocalDate date);
}
