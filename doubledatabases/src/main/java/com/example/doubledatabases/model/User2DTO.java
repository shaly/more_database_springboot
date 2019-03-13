package com.example.doubledatabases.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User2DTO implements Serializable {
    private String ename;
    private String job;
}
