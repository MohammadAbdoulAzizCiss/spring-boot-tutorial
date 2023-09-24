package com.maac.springboottutorial.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be Empty")
    private String title;

    @NotBlank(message = "Book Must Have an Author")
    private String author;

    @NotBlank(message = "Publication Year must not Be empty")
    @Positive(message = "Give a Valid Year")
    @PastOrPresent(message = "Year not valid")
    private int publicationYear;

    private String isbn;
}
