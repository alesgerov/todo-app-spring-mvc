package com.example.todoapp.notification.tokenMaker;

import java.util.UUID;

public class CreateToken {

    public static String createToken(){
        return UUID.randomUUID().toString();
    }
}
