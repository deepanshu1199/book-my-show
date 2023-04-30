package com.example.book_my_show.Convertors;

import com.example.book_my_show.DTO.ShowEntryDto;
import com.example.book_my_show.Models.MovieEntity;
import com.example.book_my_show.Models.ShowEntity;
import com.example.book_my_show.Models.TheaterEntity;

public class ShowConvertor {

    public static ShowEntity convertDtoToEntity(ShowEntryDto showEntryDto, TheaterEntity theaterEntity, MovieEntity movieEntity){
        ShowEntity showEntity=ShowEntity.builder().showType(showEntryDto.getShowType()).showDate(showEntryDto.getShowDate()).showTime(showEntryDto.getShowTime()).theaterEntity(theaterEntity).movieEntity(movieEntity).build();

        return showEntity;
    }
}
