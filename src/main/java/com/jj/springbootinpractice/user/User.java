package com.jj.springbootinpractice.user;

import com.jj.springbootinpractice.utils.validation.constraints.Password;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String userName;

    @Password
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString(){
        return "User {" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
