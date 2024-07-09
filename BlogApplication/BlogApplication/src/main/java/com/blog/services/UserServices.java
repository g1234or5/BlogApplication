package com.blog.services;




import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.blog.entity.UserEntity;
import com.blog.exception.ResourceNotFoundException;
import com.blog.model.UserDTO;
import com.blog.repository.UserRepo;

@Service
public class UserServices
{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO createUser(UserDTO userDto) {
        UserEntity user = DtoToUser(userDto);
        UserEntity saveUser = userRepo.save(user);
        return this.userToDto(saveUser);
    }

    public UserDTO updateUser(UserDTO userDto, Integer userId) {
        UserEntity user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        UserEntity updatedUser = userRepo.save(user);
        return userToDto(updatedUser);
    }

    public UserDTO getUserById(int userId) {
        UserEntity user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return this.userToDto(user);
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepo.findAll();
        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    public void deleteUser(int userId) {
        UserEntity user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepo.delete(user);
    }

    public UserEntity DtoToUser(UserDTO userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    public UserDTO userToDto(UserEntity user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
