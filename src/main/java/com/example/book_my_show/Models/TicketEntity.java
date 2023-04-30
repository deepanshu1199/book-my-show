package com.example.book_my_show.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String movieName;
    private String theaterName;
    private LocalDate showDate;
    private LocalTime showTime;
    private int totalAmount;
    private String ticketId= UUID.randomUUID().toString();
    private String bookedSeats;

    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;

    //ticket is child wrt show

    @ManyToOne
    @JoinColumn
    private ShowEntity showEntity;
}
