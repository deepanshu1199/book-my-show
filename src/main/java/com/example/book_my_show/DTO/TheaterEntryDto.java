package com.example.book_my_show.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterEntryDto {
    private String name;
    private String address;

    private int classicSeatsCount;
    private int premiumSeatsCount;
}
