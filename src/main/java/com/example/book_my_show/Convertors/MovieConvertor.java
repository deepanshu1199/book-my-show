package com.example.book_my_show.Convertors;

import com.example.book_my_show.DTO.MovieEntryDto;
import com.example.book_my_show.Models.MovieEntity;

public class MovieConvertor {
    public static MovieEntity convertDtoToMovieEntity(MovieEntryDto movieEntryDto){
        MovieEntity movieEntity=MovieEntity.builder().movieName(movieEntryDto.getMovieName()).genre(movieEntryDto.getGenre()).duration(movieEntryDto.getDuration()).language(movieEntryDto.getLanguage()).rating(movieEntryDto.getRating()).build();

        return movieEntity;
    }
}
