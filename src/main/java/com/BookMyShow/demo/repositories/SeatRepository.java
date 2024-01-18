package com.BookMyShow.demo.repositories;

import com.BookMyShow.demo.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat , Long> {

}
