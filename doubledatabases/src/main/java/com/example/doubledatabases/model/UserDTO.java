package com.example.doubledatabases.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserDTO implements Serializable {
    private String name;
    private String host;

}
