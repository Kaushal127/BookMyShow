package com.BookMyShow.demo.repositories;

import com.BookMyShow.demo.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowSeatRepository extends JpaRepository<ShowSeat , Long> {
}
