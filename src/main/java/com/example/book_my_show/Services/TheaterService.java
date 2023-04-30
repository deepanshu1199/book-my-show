package com.example.book_my_show.Services;

import com.example.book_my_show.Convertors.TheaterConvertor;
import com.example.book_my_show.DTO.TheaterEntryDto;
import com.example.book_my_show.Enums.SeatType;
import com.example.book_my_show.Models.TheaterEntity;
import com.example.book_my_show.Models.TheaterSeatEntity;
import com.example.book_my_show.Repository.TheaterRepository;
import com.example.book_my_show.Repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;
    public String addTheater(TheaterEntryDto theaterEntryDto)throws Exception{
        if(theaterEntryDto.getName()==null || theaterEntryDto.getAddress()==null){
            throw new Exception("Name or address should be valid");
        }

        TheaterEntity theaterEntity= TheaterConvertor.convertDtoToEntity(theaterEntryDto);

        List<TheaterSeatEntity> theaterSeatEntityList=createTheaterSeats(theaterEntryDto,theaterEntity);
        theaterEntity.setTheaterSeatEntityList(theaterSeatEntityList);

        theaterRepository.save(theaterEntity);
        return "Theater added successfully";
    }

    private List<TheaterSeatEntity> createTheaterSeats(TheaterEntryDto theaterEntryDto,TheaterEntity theaterEntity){
        int noClassicSeats=theaterEntryDto.getClassicSeatsCount();
        int noPremiumSeats= theaterEntryDto.getPremiumSeatsCount();

        List<TheaterSeatEntity> theaterSeatEntityList=new ArrayList<>();

        for(int count=1;count<=noClassicSeats;count++){
            TheaterSeatEntity theaterSeatEntity=TheaterSeatEntity.builder().seatType(SeatType.CLASSIC).seatNo(String.valueOf(count)+"C").theaterEntity(theaterEntity).build();
            theaterSeatEntityList.add(theaterSeatEntity);
        }

        for(int count=1;count<=noPremiumSeats;count++){
            TheaterSeatEntity theaterSeatEntity=TheaterSeatEntity.builder().seatType(SeatType.PREMIUM).seatNo(String.valueOf(count)+"P").theaterEntity(theaterEntity).build();
            theaterSeatEntityList.add(theaterSeatEntity);
        }
        // because parent is saved which is theater
       // theaterSeatRepository.saveAll(theaterSeatEntityList);

        return theaterSeatEntityList;
    }
    public List<TheaterEntity> listOfTheatersfromDB(String location){
        return theaterRepository.listOfTheaters(location);
    }
}
