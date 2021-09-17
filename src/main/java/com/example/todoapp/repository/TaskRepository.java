package com.example.todoapp.repository;

import com.example.todoapp.model.ClassForNotification;
import com.example.todoapp.model.TaskModel;
import com.example.todoapp.model.TodoUser;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<TaskModel,Long> {

    List<TaskModel> findTaskModelByUserId(Long id);

    @Query( "select t from TaskModel t " +
            "where t.userId = :idd order by t.isDone asc " )
    List<TaskModel> findTaskModelByUserIdOrderByDone(@Param("idd") Long id);

    TaskModel findTaskModelById(Long id);
    void deleteTaskModelById(Long id);

    List<TaskModel> findTaskModelByValidDateBefore(LocalDate date);

//    @Query(
//            "select tuser.email,task.title from TaskModel task join TodoUser tuser on task.userId=tuser.id " +
//                    "where task.validDate<= :date"
//    )
//    List<ClassForNotification> findEmailsForSending(@Param("date") LocalDate date);
}
