package com.example.springteacher5.service;




import com.example.springteacher5.dto.usersDTO.UserInsertDTO;
import com.example.springteacher5.dto.usersDTO.UserUpdateDTO;
import com.example.springteacher5.model.User;
import com.example.springteacher5.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IUserService {
    User insertUser(UserInsertDTO userDTO) throws Exception;
    User updateUser(UserUpdateDTO userDTO) throws EntityNotFoundException;
    User deleteUser(Long id) throws EntityNotFoundException;
    List<User> getUsersByUsername(String username) throws EntityNotFoundException;
    User getUserById(Long id) throws EntityNotFoundException;

}
