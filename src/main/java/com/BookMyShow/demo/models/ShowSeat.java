package com.BookMyShow.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{
    /*
            ShowSeat        Show
               1              1
               M              1
     */
    @ManyToOne
    private  Show show ;
    /*
            ShowSeat       Seat
                1            1
                M            1
     */
    @ManyToOne
    private Seat seat ;
    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus status ;
    private Date lockedAt ;

}
