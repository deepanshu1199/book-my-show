package com.example.book_my_show.Controllers;

import com.example.book_my_show.DTO.MovieEntryDto;
import com.example.book_my_show.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieDto){
        try{
            String response=movieService.addMovie(movieDto);
            return  new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            String result="Movie not added";
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
    }
}
