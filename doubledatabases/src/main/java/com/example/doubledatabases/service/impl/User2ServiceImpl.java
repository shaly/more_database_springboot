package com.example.doubledatabases.service.impl;

import com.example.doubledatabases.dao.primary.UserMapper;
import com.example.doubledatabases.dao.primary2.User2Mapper;
import com.example.doubledatabases.model.User2DTO;
import com.example.doubledatabases.model.UserDTO;
import com.example.doubledatabases.service.User2Service;
import com.example.doubledatabases.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User2ServiceImpl implements User2Service {
    @Autowired
    private User2Mapper user2Mapper;

    @Override
    public List<User2DTO> queryUser2() {
        return user2Mapper.queryUser2();
    }
}
