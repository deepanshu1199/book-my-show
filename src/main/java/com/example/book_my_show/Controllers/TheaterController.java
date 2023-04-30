package com.example.book_my_show.Controllers;

import com.example.book_my_show.DTO.TheaterEntryDto;
import com.example.book_my_show.Models.TheaterEntity;
import com.example.book_my_show.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    TheaterService theaterService;
    public ResponseEntity<String> addTheater(@RequestBody TheaterEntryDto theaterEntryDto){
        try{
            String response= theaterService.addTheater(theaterEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/theaterList")
    public List<TheaterEntity> listOFTheaters(@RequestParam("location") String location){
        List<TheaterEntity> theaterEntityList=theaterService.listOfTheatersfromDB(location);

        for(TheaterEntity theaterEntity:theaterEntityList){
            System.out.println(theaterEntity.getName());
        }

        return theaterEntityList;
    }
}
