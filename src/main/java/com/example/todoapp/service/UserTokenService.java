package com.example.todoapp.service;


import com.example.todoapp.model.TodoUser;
import com.example.todoapp.model.UserToken;
import com.example.todoapp.notification.SendMail;
import com.example.todoapp.notification.tokenMaker.CreateToken;
import com.example.todoapp.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.OptionalInt;


@Service
public class UserTokenService {
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private SendMail mail;

    public Optional<UserToken> getToken(String token){
        return tokenRepository.findByToken(token);
    }


    public UserToken saveToken(TodoUser user, String token){
        UserToken userToken=new UserToken();
        userToken.setToken(token);
        userToken.setTokenCreatedDate(LocalDateTime.now());
        userToken.setTokenExpiredTime(LocalDateTime.now().plusMinutes(2));
        userToken.setUser(user);
        return tokenRepository.save(userToken);
    }

    public boolean checkToken(String tokenString){
        UserToken token=null;
        Optional<UserToken> optionalUserToken=getToken(tokenString);
        if (optionalUserToken.isPresent()){
            token=optionalUserToken.get();
            if (LocalDateTime.now().isAfter(token.getTokenExpiredTime())){
                tokenRepository.deleteByToken(tokenString);
                return false;
            }
            else {
                return true;
            }
        }
        return false;
    }

    public void deleteExpired(){
        tokenRepository.deleteUserTokensByTokenExpiredTimeBefore(LocalDateTime.now());
    }

    public void sendToken(TodoUser user, HttpServletRequest request){
        int length=request.getRequestURI().length();
        StringBuffer  buffer=request.getRequestURL().reverse().delete(0,length-1).reverse();
        buffer.append("activate?token=");
        String token= CreateToken.createToken();
        buffer.append(token);
        saveToken(user,token);
        mail.sendMail(user.getEmail(),buffer.toString());
    }

    public void sendTokenForForgot(TodoUser user, HttpServletRequest request){
        int length=request.getRequestURI().length();
        StringBuffer  buffer=request.getRequestURL().reverse().delete(0,length-1).reverse();
        buffer.append("forgot/password?token=");
        String token= CreateToken.createToken();
        buffer.append(token);
        saveToken(user,token);
        mail.sendMail(user.getEmail(),buffer.toString());
    }

}
