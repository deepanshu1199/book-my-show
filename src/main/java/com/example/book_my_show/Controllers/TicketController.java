package com.example.book_my_show.Controllers;

import com.example.book_my_show.DTO.TicketEntryDto;
import com.example.book_my_show.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("/book")
    public String addTicket(@RequestBody TicketEntryDto ticketEntryDto){
        try{
            String response=ticketService.addTicket(ticketEntryDto);
            return response;
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
