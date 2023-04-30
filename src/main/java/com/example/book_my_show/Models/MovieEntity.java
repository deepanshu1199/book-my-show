package com.example.book_my_show.Models;

import com.example.book_my_show.Enums.Genre;
import com.example.book_my_show.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Movies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true,nullable = false)
    private String movieName;
    private int duration;
    private double rating;
    @Enumerated(value = EnumType.STRING)
    private Language language;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    //parent with respect to show

    @OneToMany(mappedBy = "movieEntity",cascade = CascadeType.ALL)
    private List<ShowEntity> showEntityList=new ArrayList<>();
}
