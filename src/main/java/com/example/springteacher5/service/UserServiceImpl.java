package com.example.springteacher5.service;

import com.example.springteacher5.dto.teacherDTOs.TeacherInsertDTO;
import com.example.springteacher5.dto.teacherDTOs.TeacherUpdateDTO;
import com.example.springteacher5.dto.usersDTO.UserInsertDTO;
import com.example.springteacher5.dto.usersDTO.UserUpdateDTO;
import com.example.springteacher5.mapper.Mapper;

import com.example.springteacher5.model.User;
import com.example.springteacher5.repository.UserRepository;
import com.example.springteacher5.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;


    @Transactional
    @Override
    public User insertUser(UserInsertDTO dto) throws Exception {
        User user = null;

        try{
            user = userRepository.save(Mapper.mapToUser(dto));
            if(user.getId()==null){
                throw new Exception("Insert error");
            }
            log.info("insert succes for user with id"+ user.getId());
            return user;
        }catch (Exception e){
            log.error("insert error "+ e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public User updateUser(UserUpdateDTO dto) throws EntityNotFoundException {
        User user;
        User updatedUser;
        try {
            user = userRepository.findUsersById(dto.getId());
            if (user == null) throw new EntityNotFoundException(User.class, dto.getId());
            updatedUser = userRepository.save(Mapper.mapToUser(dto));
            log.info("User with id: "+ updatedUser.getId()+ " was updated");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return updatedUser;
    }

    @Transactional
    @Override
    public User deleteUser(Long id) throws EntityNotFoundException {
        User user = null;

        try {
            user =  userRepository.findUsersById(id);
            if (user == null) throw new EntityNotFoundException(User.class,id);
            userRepository.deleteById(id);
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return user;
    }

    @Override
    public List<User> getUsersByUsername(String username) throws EntityNotFoundException {
        List<User> users = new ArrayList<>();
        try {
            users = userRepository.findByUsernameStartingWith(username);
            if (users.isEmpty()) throw new EntityNotFoundException(User.class,0L);
            log.info("Users with lastname starting with "+ username +" were found");
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return users;
    }

    @Override
    public User getUserById(Long id) throws EntityNotFoundException {
        User user;
        try {
            user = userRepository.findUsersById(id);
            if(user==null)throw new EntityNotFoundException(User.class,id);
        }catch (EntityNotFoundException e){
            log.error(e.getMessage());
            throw e;
        }
        return user;
    }

}

