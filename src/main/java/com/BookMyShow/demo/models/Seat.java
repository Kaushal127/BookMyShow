package com.BookMyShow.demo.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private int number ;
    private String seatType ;
    private int row ;
    private int col ;


}
