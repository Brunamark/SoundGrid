package com.soundgrid.api.infraestructure.adapter.output.peristence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.soundgrid.api.infraestructure.adapter.output.peristence.entity.MusicEntity;


@Repository
public interface SpringDataMusicRepository extends JpaRepository<MusicEntity, Long> {

    List<MusicEntity> findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCase(String title, String artist);
    boolean existsById(@NonNull Long id);
    
} 
