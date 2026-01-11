package com.project.calendario.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.calendario.entity.DashboardEntity;
import com.project.calendario.entity.UsuarioEntity;

@Repository
public interface DashboardRepository extends JpaRepository<DashboardEntity, Long> {
    Page<DashboardEntity> findByUsuario(UsuarioEntity usuario, Pageable pageable);
}
