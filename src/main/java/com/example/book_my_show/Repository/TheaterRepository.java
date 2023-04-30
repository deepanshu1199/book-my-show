package com.example.book_my_show.Repository;

import com.example.book_my_show.Models.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TheaterRepository extends JpaRepository<TheaterEntity,Integer> {
    //@Query(value = "select * from theater where lo")
    public List<TheaterEntity> listOfTheaters(String location);
}
