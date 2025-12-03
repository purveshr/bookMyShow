package com.backendlld.bookmyshowjan.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(
        name = "movie",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_movie_title_director",
                        columnNames = {"title", "director"}
                )
        }
)
public class Movie extends BaseModel{
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private String title;
    private String director;
    private String genre;
    private String description;
    private int durationMins;
    private Integer rating;

}
