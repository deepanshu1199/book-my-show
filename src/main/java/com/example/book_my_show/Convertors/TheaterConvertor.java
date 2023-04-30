package com.example.book_my_show.Convertors;

import com.example.book_my_show.DTO.TheaterEntryDto;
import com.example.book_my_show.Models.TheaterEntity;

public class TheaterConvertor {
    public static TheaterEntity convertDtoToEntity(TheaterEntryDto theaterEntryDto){
        TheaterEntity theaterEntity=TheaterEntity.builder().name(theaterEntryDto.getName()).address(theaterEntryDto.getAddress()).build();

        return theaterEntity;
    }
}
