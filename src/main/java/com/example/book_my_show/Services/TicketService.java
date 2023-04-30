package com.example.book_my_show.Services;

import com.example.book_my_show.Convertors.TicketConvertor;
import com.example.book_my_show.DTO.ShowEntryDto;
import com.example.book_my_show.DTO.TicketEntryDto;
import com.example.book_my_show.Models.ShowEntity;
import com.example.book_my_show.Models.ShowSeatEntity;
import com.example.book_my_show.Models.TicketEntity;
import com.example.book_my_show.Models.UserEntity;
import com.example.book_my_show.Repository.ShowRepository;
import com.example.book_my_show.Repository.TicketRepository;
import com.example.book_my_show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;

    public String addTicket(TicketEntryDto ticketEntryDto)throws Exception{
        TicketEntity ticketEntity=TicketConvertor.convertDtoToEntity(ticketEntryDto);
        ShowEntity showEntity=showRepository.findById(ticketEntryDto.getShowId()).get();
        UserEntity userEntity=userRepository.findById(ticketEntryDto.getUserId()).get();

        //check weather list of requestedSeats are available or not

        boolean isValid=checkRequestedSeatsAvailability(ticketEntryDto);
        if(isValid==false){
            throw new Exception("requested seats are not available");
        }

        //calculating amount if isValid ==true
        int totalAmount=totalAmount(ticketEntryDto);

        ticketEntity.setShowDate(showEntity.getShowDate());
        ticketEntity.setShowTime(showEntity.getShowTime());
        ticketEntity.setMovieName(showEntity.getMovieEntity().getMovieName());
        ticketEntity.setTheaterName(showEntity.getTheaterEntity().getName());
        ticketEntity.setTotalAmount(totalAmount);
        ticketEntity.setShowEntity(showEntity);
        ticketEntity.setUserEntity(userEntity);

        String allotedSeats=getallotedSeats(showEntity.getListOfShowSeats());
        ticketEntity.setBookedSeats(allotedSeats);

        List<TicketEntity> ticketEntityList=showEntity.getListOfBookedTickets();
        ticketEntityList.add(ticketEntity);
        showEntity.setListOfBookedTickets(ticketEntityList);

        showRepository.save(showEntity);

        List<TicketEntity> ticketEntityList1=userEntity.getBookedTickets();
        ticketEntityList1.add(ticketEntity);
        userEntity.setBookedTickets(ticketEntityList1);

        userRepository.save(userEntity);

        return "Ticket is booked successfully";
    }
    private String getallotedSeats(List<ShowSeatEntity> showSeatEntityList){
        String result="";

        for(ShowSeatEntity showSeatEntity: showSeatEntityList){
            result=result+showSeatEntity.getSeatNo()+", ";
        }
        return result;
    }
    private boolean checkRequestedSeatsAvailability(TicketEntryDto ticketEntryDto){
        ShowEntity showEntity=showRepository.findById(ticketEntryDto.getShowId()).get();

        List<String> requestedSeats=ticketEntryDto.getRequestedSeats();

        List<ShowSeatEntity> listOfSeats=showEntity.getListOfShowSeats();

        for(ShowSeatEntity showSeatEntity: listOfSeats){
            String seatNo=showSeatEntity.getSeatNo();
            if(!requestedSeats.contains(seatNo) || showSeatEntity.isBooked()==true){
                return false;
            }
        }
        return true;
    }
    private int totalAmount(TicketEntryDto ticketEntryDto){
        ShowEntity showEntity=showRepository.findById(ticketEntryDto.getShowId()).get();
        int amount=0;
        List<String> requestedSeats=ticketEntryDto.getRequestedSeats();

        List<ShowSeatEntity> showSeatEntityList=showEntity.getListOfShowSeats();

        for(ShowSeatEntity showSeatEntity: showSeatEntityList){
            if(requestedSeats.contains(showSeatEntity)){
                amount+=showSeatEntity.getPrice();
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedAt(new Date());
            }
        }
        return amount;
    }
}
