package com.project.calendario.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.calendario.entity.DashboardEntity;
import com.project.calendario.entity.TareaEntity;

@Repository
public interface TareaRepository extends JpaRepository<TareaEntity, Long> {
    Page<TareaEntity> findByDashboard(DashboardEntity dashboard, Pageable pageable);

    Page<TareaEntity> findByDashboardUsuarioId(Long usuarioId, Pageable pageable);

    Long countByDashboard(DashboardEntity dashboard);

}
