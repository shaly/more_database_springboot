package com.example.doubledatabases.dao.primary2;

import com.example.doubledatabases.model.User2DTO;
import com.example.doubledatabases.model.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User2Mapper {
    List<User2DTO> queryUser2();
}
