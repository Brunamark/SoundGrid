package com.soundgrid.api.infraestructure.adapter.output.peristence.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.soundgrid.api.domain.types.Genre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "music")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Size(max = 100, message = "The title cannot be longer than 100 characters.")
    private String title;
    @Column(nullable = false)
    @Size(max = 50, message = "Artist name cannot be longer than 50 characters.")
    private String artist;

    @Size(max = 100, message = "The album cannot be longer than 100 characters.")
    private String album;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;
    @Column(nullable = false, name = "release_date")
    @PastOrPresent(message = "The release date cannot be in the future.")
    private LocalDate releaseDate;
    @Min(value = 10, message = "Duration must be at least 10 seconds.")
    @Max(value = 600, message = "Duration cannot be greater than 600 seconds.")
    private int duration;
    @Column(nullable = false, name = "file_path")
    @Size(max = 100, min=10, message = "The file path cannot be longer than 100 characters.")
    private String filePath;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
