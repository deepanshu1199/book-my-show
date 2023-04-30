package com.example.book_my_show.Services;

import com.example.book_my_show.Convertors.ShowConvertor;
import com.example.book_my_show.DTO.ShowEntryDto;
import com.example.book_my_show.Enums.SeatType;
import com.example.book_my_show.Models.*;
import com.example.book_my_show.Repository.MovieRepository;
import com.example.book_my_show.Repository.ShowRepository;
import com.example.book_my_show.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    MovieRepository movieRepository;

    public String addShow(ShowEntryDto showEntryDto)throws Exception{
        if(!theaterRepository.existsById(showEntryDto.getTheaterId()) || !movieRepository.existsById(showEntryDto.getMovieId())){
            throw new Exception("theater or movie should be valid");
        }
        //setting showEntity,theaterEntity and MovieEntity
        TheaterEntity theaterEntity=theaterRepository.findById(showEntryDto.getTheaterId()).get();
        MovieEntity movieEntity=movieRepository.findById(showEntryDto.getMovieId()).get();
        ShowEntity showEntity= ShowConvertor.convertDtoToEntity(showEntryDto,theaterEntity,movieEntity);

        //creating showSeatEntity

        List<ShowSeatEntity> showSeatEntityList=createShowSeat(showEntryDto,showEntity);

        showEntity.setListOfShowSeats(showSeatEntityList);

        List<ShowEntity> showEntityList=theaterEntity.getShowEntityList();
        showEntityList.add(showEntity);
        theaterEntity.setShowEntityList(showEntityList);

        List<ShowEntity> showEntityList1=movieEntity.getShowEntityList();
        showEntityList1.add(showEntity);
        movieEntity.setShowEntityList(showEntityList1);

        theaterRepository.save(theaterEntity);
        movieRepository.save(movieEntity);

        return "Show added successfully";
    }
    private List<ShowSeatEntity> createShowSeat(ShowEntryDto showEntryDto,ShowEntity showEntity){
        List<TheaterSeatEntity> theaterSeatEntityList=showEntity.getTheaterEntity().getTheaterSeatEntityList();

        List<ShowSeatEntity> showSeatEntityList=new ArrayList<>();

        for(TheaterSeatEntity theaterSeatEntity:theaterSeatEntityList){
            ShowSeatEntity showSeatEntity=new ShowSeatEntity();

            showSeatEntity.setSeatNo(theaterSeatEntity.getSeatNo());
            showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());

            if(theaterSeatEntity.getSeatType().equals(SeatType.CLASSIC)){
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());
            }
            else{
                showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());
            }
            showSeatEntity.setBooked(false);
            showSeatEntity.setShowEntity(showEntity); // setting up foreign key

            showSeatEntityList.add(showSeatEntity);
        }

        return showSeatEntityList;
    }
}
