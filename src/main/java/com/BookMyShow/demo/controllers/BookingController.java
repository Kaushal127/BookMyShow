package com.BookMyShow.demo.controllers;

import com.BookMyShow.demo.dtos.BookShowRequestDto;
import com.BookMyShow.demo.dtos.BookShowResponseDto;
import com.BookMyShow.demo.dtos.ResponseStatus;
import com.BookMyShow.demo.exceptions.SeatNotAvailable;
import com.BookMyShow.demo.exceptions.ShowNotFound;
import com.BookMyShow.demo.exceptions.UserIsNotValid;
import com.BookMyShow.demo.models.Booking;
import com.BookMyShow.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    private static final String USER_INVALID_MESSAGE = "User id Invalid";
    private static final String SHOW_NOT_FOUND = "Show id Invalid";
    private static final String SOMETHING_WENT_WRONG = "Something went wrong";
    private BookingService bookingService ;
    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService ;
    }
    public BookShowResponseDto bookShow(BookShowRequestDto request){
        try {
            Booking booking = bookingService.bookShow(request) ;
            return new BookShowResponseDto(booking.getId() , booking.getAmount() , ResponseStatus.SUCESS , "SUCESS" ) ;
        } catch (UserIsNotValid e) {
            return new BookShowResponseDto(null , 0 ,ResponseStatus.FAILURE , USER_INVALID_MESSAGE) ;
        } catch (ShowNotFound e) {
            return new BookShowResponseDto(null ,0,ResponseStatus.FAILURE , SHOW_NOT_FOUND) ;
        } catch (SeatNotAvailable e) {
            return new BookShowResponseDto(null ,0,ResponseStatus.FAILURE , SOMETHING_WENT_WRONG) ;
        }

    }
}
