package com.BookMyShow.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Screen extends  BaseModel{
    private String name ;
    /*
        Screen      Seat
          1           M
          1           1
     */
    @OneToMany
    private List<Seat> seats ;
    /*
        Screen      Feature
            1          M
            M          1
     */
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features ;

}
