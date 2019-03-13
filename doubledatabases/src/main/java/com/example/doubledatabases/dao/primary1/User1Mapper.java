package com.example.doubledatabases.dao.primary1;

import com.example.doubledatabases.model.User1DTO;
import com.example.doubledatabases.model.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User1Mapper {
    List<User1DTO> queryUser1();
}
