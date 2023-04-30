package com.example.book_my_show.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketEntryDto {
    private int showId;
    private int userId;
    private List<String> requestedSeats=new ArrayList<>();
}
