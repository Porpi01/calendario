package com.project.calendario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.calendario.entity.EventoEntity;

@Repository
public interface EventoRepository extends JpaRepository<EventoEntity, Long> {


    
}
