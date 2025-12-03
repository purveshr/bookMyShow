package com.backendlld.bookmyshowjan.repos;

import com.backendlld.bookmyshowjan.models.Movie;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    List<Movie> findAll();
    Boolean existsByTitleAndDirector(String title, String director);
    List<Movie> findByGenreContainingIgnoreCase(String genre);

    @Query("SELECT m FROM Movie m WHERE m.id = :keyword")
    Movie findByMovieId(@Param("keyword") String id);

//    @Query("SELECT m FROM Movie m WHERE LOWER(m.title) LIKE LOWER('%', :keyword, '%')")
//    List<Movie> findByTitleContainingIgnoreCase(@Param("keyword") String keyword);

    List<Movie> findByTitleContainingIgnoreCase(String title);

}
