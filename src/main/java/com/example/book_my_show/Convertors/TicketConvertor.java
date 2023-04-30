package com.example.book_my_show.Convertors;

import com.example.book_my_show.DTO.TicketEntryDto;
import com.example.book_my_show.Models.ShowEntity;
import com.example.book_my_show.Models.TicketEntity;
import com.example.book_my_show.Models.UserEntity;

public class TicketConvertor {
    public static TicketEntity convertDtoToEntity(TicketEntryDto ticketEntryDto){
        TicketEntity ticketEntity=new TicketEntity();
        return ticketEntity;
    }
}
