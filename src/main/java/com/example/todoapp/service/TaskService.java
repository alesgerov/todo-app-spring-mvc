package com.example.todoapp.service;

import com.example.todoapp.form.TaskForm;
import com.example.todoapp.model.ClassForNotification;
import com.example.todoapp.model.TaskModel;
import com.example.todoapp.model.TodoUser;
import com.example.todoapp.notification.SendMail;
import com.example.todoapp.repository.NotificationRepo;
import com.example.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SendMail sendMail;

    @Autowired
    private NotificationRepo notificationRepo;

    public List<TaskModel> getTaskModels(TodoUser user){
        return taskRepository.findTaskModelByUserId(user.getId());
    }

    public List<TaskModel> getTaskModelsOrder(TodoUser user){
        return taskRepository.findTaskModelByUserIdOrderByDone(user.getId());
    }

    public TaskModel saveTask(TodoUser user,TaskModel model){
        model.setUserId(user.getId());
        return taskRepository.save(model);
    }


    public TaskModel saveTask(TodoUser user, TaskForm taskForm){
        TaskModel model=new TaskModel();

        model.setUserId(user.getId());
        model.setCreatedTime(LocalDateTime.now());
        model.setDescription(taskForm.getDescription());
        model.setNeedNotification(taskForm.isNeedNotification());
        model.setTitle(taskForm.getTitle());
        model.setValidDate(taskForm.getValidDate());
        model.setDone(false);
        return taskRepository.save(model);
    }

    public TaskModel makeTaskDone(long id){
        TaskModel taskModel=taskRepository.findTaskModelById(id);
        taskModel.setDone(true);

        return taskRepository.save(taskModel);
    }

    public TaskModel makeTaskUndone(long id){
        TaskModel taskModel=taskRepository.findTaskModelById(id);
        taskModel.setDone(false);

        return taskRepository.save(taskModel);
    }


    public void deleteTask(long id) {
        taskRepository.deleteTaskModelById(id);
    }

    public void sendNotification(LocalDate localDate){
        List<ClassForNotification> notifications=notificationRepo.findEmailsForSending(localDate.plusDays(1));
        notifications.forEach((classForNotification -> {
            sendMail.sendMail(classForNotification.getEmail(),classForNotification.getTitleOfTask(),"Task deadline time is close.");
        }));
//        notifications.forEach(System.out::println);
    }


}
