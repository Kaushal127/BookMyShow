package com.BookMyShow.demo.repositories;

import com.BookMyShow.demo.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show , Long> {
}
