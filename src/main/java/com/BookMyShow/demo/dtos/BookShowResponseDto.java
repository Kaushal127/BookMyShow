package com.BookMyShow.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookShowResponseDto {
    private Long bookingId ;
    private int amount ;
    private ResponseStatus responseStatus ;
    private String failureReason ;

}
