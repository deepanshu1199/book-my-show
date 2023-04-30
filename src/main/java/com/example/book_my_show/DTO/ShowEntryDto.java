package com.example.book_my_show.DTO;

import com.example.book_my_show.Enums.ShowType;
import com.example.book_my_show.Models.MovieEntity;
import com.example.book_my_show.Models.ShowSeatEntity;
import com.example.book_my_show.Models.TheaterEntity;
import com.example.book_my_show.Models.TicketEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowEntryDto {
    private ShowType showType;
    private LocalDate showDate;
    private LocalTime showTime;
    private int TheaterId;
    private int MovieId;
    private int premiumSeatPrice;
    private int classicSeatPrice;
}
