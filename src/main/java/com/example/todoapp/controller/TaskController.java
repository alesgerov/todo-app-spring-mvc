package com.example.todoapp.controller;

import com.example.todoapp.form.TaskForm;
import com.example.todoapp.model.TaskModel;
import com.example.todoapp.service.TaskService;
import com.example.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@RequestMapping
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @GetMapping({"/task/add", "/task/add"})
    public ModelAndView addTask() {
        ModelAndView modelAndView = new ModelAndView();
        TaskForm taskForm = new TaskForm();
        if (userService.isActivated(userService.getCurrentUser())) {
            modelAndView.setViewName("add_task");
            modelAndView.addObject("form", taskForm);
        } else {
            modelAndView.setViewName("should-activate");
        }

        return modelAndView;
    }

    @PostMapping({"/task/add", "/task/add"})
    public ModelAndView addTaskPost(@ModelAttribute(name = "form") TaskForm form,
                                    @RequestParam(name = "deadline") String deadline
    ) {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.isActivated(userService.getCurrentUser())) {
            LocalDateTime localDateTime = LocalDateTime.now();
            modelAndView.addObject("localDateTime", localDateTime);
            deadline = deadline.replace('T', ' ');
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate time = LocalDate.parse(deadline, formatter);
            form.setValidDate(time);
            taskService.saveTask(userService.getCurrentUser(), form);
            modelAndView.setViewName("redirect:/tasks");
        } else {
            modelAndView.setViewName("should-activate");

        }
        return modelAndView;
    }


    @PostMapping({"/done/{id}", "/done/{id}"})
    public String makeDone(@PathVariable(name = "id") long id) {
        if (userService.isActivated(userService.getCurrentUser())) {
            taskService.makeTaskDone(id);
            return "redirect:/tasks";
        } else {
            return "should-activate";
        }
    }

    @GetMapping({"/done/{id}", "/done/{id}"})
    public String makeDoneGet(@PathVariable(name = "id") long id) {
        if (userService.isActivated(userService.getCurrentUser())) {
            taskService.makeTaskDone(id);
            return "redirect:/tasks";
        } else {
            return "should-activate";
        }

    }


    @PostMapping({"/delete/{id}", "/delete/{id}"})
    public String deleteTask(@PathVariable(name = "id") long id) {
        if (userService.isActivated(userService.getCurrentUser())) {
            taskService.deleteTask(id);
            return "redirect:/tasks";
        } else {
            return "should-activate";
        }
    }

    @GetMapping({"/delete/{id}", "/delete/{id}"})
    public String deleteTaskGet(@PathVariable(name = "id") long id) {
        if (userService.isActivated(userService.getCurrentUser())) {
            taskService.deleteTask(id);
            return "redirect:/tasks";
        } else {
            return "should-activate";
        }
    }

    @PostMapping({"/undone/{id}", "/undone/{id}"})
    public String makeUndone(@PathVariable(name = "id") long id) {
        if (userService.isActivated(userService.getCurrentUser())) {
            taskService.makeTaskUndone(id);
            return "redirect:/tasks";
        } else {
            return "should-activate";
        }
    }

    @GetMapping({"/undone/{id}", "/undone/{id}"})
    public String makeUndoneGet(@PathVariable(name = "id") long id) {
        if (userService.isActivated(userService.getCurrentUser())) {
            taskService.makeTaskUndone(id);
            return "redirect:/tasks";
        } else {
            return "should-activate";
        }
    }


    @GetMapping({"/tasks", "/", "/index","/tasks/", "/index/"})
    public ModelAndView tasksOrder() {
        ModelAndView modelAndView = new ModelAndView();
        if (userService.isActivated(userService.getCurrentUser())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:ss");
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            modelAndView.setViewName("tasks");
            List<TaskModel> tasks = taskService.getTaskModelsOrder(userService.getCurrentUser());
            modelAndView.addObject("tasks", tasks);
            modelAndView.addObject("formatter", formatter);
            modelAndView.addObject("formatter1", formatter1);
        } else {
            modelAndView.setViewName("should-activate");
        }
        return modelAndView;
    }
}

