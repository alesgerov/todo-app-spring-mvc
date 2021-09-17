package com.example.todoapp.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Transactional
@Setter
public class TodoUser  {
    @Id
    @SequenceGenerator(
            name = "todo_users_id_seq",
            sequenceName = "todo_users_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "todo_users_id_seq"
    )
    private Long id;
    private String email;
    private String username;
    private String password;
    private String role=TodoRole.USER_ROLE;
    private String status=TodoStatus.WAITING;

}
