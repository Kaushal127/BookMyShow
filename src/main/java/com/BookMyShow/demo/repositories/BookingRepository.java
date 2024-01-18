package com.BookMyShow.demo.repositories;

import com.BookMyShow.demo.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking , Long> {
}
