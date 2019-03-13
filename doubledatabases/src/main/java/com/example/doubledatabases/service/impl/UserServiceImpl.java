package com.example.doubledatabases.service.impl;

import com.example.doubledatabases.model.UserDTO;
import com.example.doubledatabases.dao.primary.UserMapper;
import com.example.doubledatabases.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> queryUser() {
        return userMapper.queryUser();
    }
}
