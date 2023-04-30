package com.example.book_my_show.Models;

import com.example.book_my_show.Enums.ShowType;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(value = EnumType.STRING)
    private ShowType showType;
    private LocalDate showDate;
    private LocalTime showTime;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

    @ManyToOne
    @JoinColumn
    private MovieEntity movieEntity;

    @ManyToOne
    @JoinColumn
    private TheaterEntity theaterEntity;

    @OneToMany(mappedBy = "showEntity",cascade = CascadeType.ALL)
    private List<TicketEntity> listOfBookedTickets=new ArrayList<>();

    @OneToMany(mappedBy = "showEntity",cascade = CascadeType.ALL)
    private List<ShowSeatEntity> listOfShowSeats=new ArrayList<>();
}
