package com.example.todoapp.model;


import lombok.Data;

public class ClassForNotification {
     private String email;
     private String titleOfTask;

     public ClassForNotification(String email, String titleOfTask) {
          this.email = email;
          this.titleOfTask = titleOfTask;
     }

     @Override
     public String toString() {
          return "ClassForNotification{" +
                  "email='" + email + '\'' +
                  ", titleOfTask='" + titleOfTask + '\'' +
                  '}';
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getTitleOfTask() {
          return titleOfTask;
     }

     public void setTitleOfTask(String titleOfTask) {
          this.titleOfTask = titleOfTask;
     }
}
