package com.example.doubledatabases.dao.primary;

import com.example.doubledatabases.model.UserDTO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<UserDTO> queryUser();
}
