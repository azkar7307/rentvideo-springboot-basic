package com.crio.rentvideo_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Video {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "title must not be blank")
    private String title;

    @NotBlank(message = "director name must not be blank")
    private String director;

    @NotBlank(message = "genre must not be blank")
    private String genre;

    private boolean available;
}
