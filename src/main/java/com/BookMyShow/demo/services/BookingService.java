package com.BookMyShow.demo.services;

import com.BookMyShow.demo.dtos.BookShowRequestDto;
import com.BookMyShow.demo.exceptions.SeatNotAvailable;
import com.BookMyShow.demo.exceptions.ShowNotFound;
import com.BookMyShow.demo.exceptions.UserIsNotValid;
import com.BookMyShow.demo.models.*;
import com.BookMyShow.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository ;
    private ShowRepository showRepository ;
    private ShowSeatRepository showSeatRepository ;
    private BookingRepository bookingRepository ;
    private SeatRepository seatRepository ;

    @Autowired
    public BookingService(UserRepository userRepository , ShowRepository showRepository , ShowSeatRepository showSeatRepository , BookingRepository bookingRepository , SeatRepository seatRepository){
        this.userRepository = userRepository ;
        this.showRepository = showRepository ;
        this.showSeatRepository = showSeatRepository ;
        this.bookingRepository = bookingRepository ;
        this.seatRepository = seatRepository ;
    }

    public Booking bookShow(BookShowRequestDto request) throws UserIsNotValid, ShowNotFound, SeatNotAvailable {
        // get user with userId
        Optional<User> user = userRepository.findById(request.getUserId()) ;
        if (!user.isPresent()){
            throw new UserIsNotValid() ;
        }
        // get show with showId
        Optional<Show> show = showRepository.findById(request.getShowId()) ;
        if (!show.isPresent()){
            throw new ShowNotFound() ;
        }

        List<ShowSeat> reserveShowSeat = reserveShowSeats(request.getShowSeatIds()) ;

        return  reserveBooking(request , user , reserveShowSeat) ;

    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<ShowSeat> reserveShowSeats(List<Long> showSeatIds) throws SeatNotAvailable {
        // get list <showSeat> for showSeatIds
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds) ;
        //checking if any of the showSeats are already reserved -> throw an error
        for (ShowSeat showSeat : showSeats){
            seatNotAvailabaleForBooking(showSeat) ;
        }
        /*  here we do locking , only if
               1. all the seats are available OR
               2. if all the seats are locked and lockduration > 10
         */
        List <ShowSeat> reservedShowSeats = new ArrayList<>() ;
          for (ShowSeat showSeat : showSeats){
              showSeat.setStatus(ShowSeatStatus.LOCKED);
              showSeat.setLockedAt(new Date());
              reservedShowSeats.add(showSeatRepository.save(showSeat));
          }
          return reservedShowSeats ;
    }

    private static boolean seatNotAvailabaleForBooking(ShowSeat showSeat) throws SeatNotAvailable {
        // refactor and make it understandeable
        /* here we do locking , only if
            1. all the seats are available OR
            2. if all the seats are locked and lockedDuration > 10
         */
        if (!ShowSeatStatus.AVAILABLE.equals(showSeat.getStatus())) {
            if(ShowSeatStatus.BOOKED.equals(showSeat.getStatus())){
                throw new SeatNotAvailable() ;
            }
            if(ShowSeatStatus.LOCKED.equals(showSeat.getStatus())){
                Long lockedDuration = Duration.between(showSeat.getLockedAt().toInstant(),new Date().toInstant()).toMinutes() ;
                if (lockedDuration < 10){
                    throw new SeatNotAvailable() ;
                }
            }
        }
        return true ;
        //     return !ShowSeatStatus.AVAILABLE.equals(showSeat.getStatus()) ||
        //     (ShowSeatStatus.LOCKED.equals(showSeat.getStatus())
        //     && Duration.between(showSeat.getLockedAt().toInstant(), new Date().toInstant()).toMinutes() < 10);
    }


    private Booking reserveBooking(BookShowRequestDto request, Optional<User> user, List<ShowSeat> reserveShowSeat) {
        Booking booking = new Booking() ;
        booking.setStatus(BookingStatus.PENDING);
        booking.setAmount(priceCalculator(request.getShowSeatIds() , request.getShowId()));
        booking.setUser(user.get());
        booking.setShowSeatList(reserveShowSeat);
        booking.setPayments(new ArrayList<>());

        return bookingRepository.save(booking) ;
    }

    private int priceCalculator(List<Long> showSeatIds, Long showId) {
        // get the show
        Optional<Show> show = showRepository.findById(showId) ;
        int totalprice = 0 ;

        for (Long showSeatId : showSeatIds){
            // get the  seats
            Optional<Seat> seat = seatRepository.findById(showSeatId) ;
            // calculate the price based on seat type
          //  int seatPrice = calculateSeatPrice(showId ,seat) ;
          //  totalprice+=seatPrice ;
        }
        // for the seats_ids , you can find the seat type
        // for the pair (showId , seatType ) -> price
        // sum it up for all seats selected
        return 0;
    }


}
