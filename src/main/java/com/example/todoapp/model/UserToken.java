package com.example.todoapp.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Transactional
@Setter
public class UserToken {
    @Id
    @SequenceGenerator(
            name = "token_id_seq",
            sequenceName = "token_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "token_id_seq"
    )
    private Long id;
    private String token;
    @ManyToOne
    private TodoUser user;
    private LocalDateTime tokenCreatedDate;
    private LocalDateTime tokenExpiredTime;
}
