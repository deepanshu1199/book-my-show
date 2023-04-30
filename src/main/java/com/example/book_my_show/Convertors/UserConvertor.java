package com.example.book_my_show.Convertors;

import com.example.book_my_show.DTO.UserEntryDto;
import com.example.book_my_show.Models.UserEntity;

public class UserConvertor {
    public static UserEntity convertDtoToEntity(UserEntryDto userEntryDto){
        UserEntity userEntity=UserEntity.builder().age(userEntryDto.getAge()).mobNo(userEntryDto.getMobNo()).name(userEntryDto.getName()).email(userEntryDto.getEmail()).address(userEntryDto.getAddress()).build();

        return userEntity;
    }
}
