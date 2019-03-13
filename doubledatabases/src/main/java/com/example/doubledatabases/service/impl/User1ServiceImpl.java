package com.example.doubledatabases.service.impl;

import com.example.doubledatabases.dao.primary.UserMapper;
import com.example.doubledatabases.dao.primary1.User1Mapper;
import com.example.doubledatabases.model.User1DTO;
import com.example.doubledatabases.model.UserDTO;
import com.example.doubledatabases.service.User1Service;
import com.example.doubledatabases.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User1ServiceImpl implements User1Service {
    @Autowired(required=true)
    private User1Mapper user1Mapper;

    @Override
    public List<User1DTO> queryUser() {
        return user1Mapper.queryUser1();
    }
}
