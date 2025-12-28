package com.example.springaidevelopment.beanconvertercontroller;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Movie {
    private String movieName;
    private String actorName;
    private String directorName;
    private String year;
    private String genre;
    private String actressName;
}
