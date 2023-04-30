package com.example.book_my_show.Services;

import com.example.book_my_show.Convertors.UserConvertor;
import com.example.book_my_show.DTO.UserEntryDto;
import com.example.book_my_show.Models.UserEntity;
import com.example.book_my_show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String addUser(UserEntryDto userEntryDto)throws Exception{
        UserEntity userEntity= UserConvertor.convertDtoToEntity(userEntryDto);
        userRepository.save(userEntity);

        return "User Added Successfully";
    }
}
