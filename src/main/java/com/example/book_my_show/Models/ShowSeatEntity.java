package com.example.book_my_show.Models;

import com.example.book_my_show.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "show_seats")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowSeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean isBooked;
    private int price;
    private String seatNo;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    private Date bookedAt;

    @ManyToOne
    @JoinColumn
    private ShowEntity showEntity;
}
