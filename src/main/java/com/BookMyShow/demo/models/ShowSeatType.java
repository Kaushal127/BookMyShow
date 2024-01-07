package com.BookMyShow.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel{

    /*
            ShowSeatType      Show
               1               1
               M               1
     */
    @ManyToOne
    private Show show ;
    private String seatType ;
    private int price ;

}
