package com.project.calendario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.calendario.entity.DashboardEntity;
import com.project.calendario.entity.UsuarioEntity;
import com.project.calendario.exception.ResourceNotFoundException;
import com.project.calendario.repository.DashboardRepository;

@Service
public class DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private UsuarioService usuarioService;

    public DashboardEntity get(Long id) {
        return dashboardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dashboard no encontrado: " + id));
    }

    public Long create(DashboardEntity dashboardEntity) {
        // validar usuario
        UsuarioEntity usuario = usuarioService.get(dashboardEntity.getUsuario().getId());
        dashboardEntity.setUsuario(usuario);

        dashboardRepository.save(dashboardEntity);
        return dashboardEntity.getId();
    }

    public DashboardEntity update(DashboardEntity dashboardEntity) {
        get(dashboardEntity.getId());
        return dashboardRepository.save(dashboardEntity);
    }

    public Long delete(Long id) {
        dashboardRepository.deleteById(id);
        return id;
    }

    public Page<DashboardEntity> getByUsuario(Long usuarioId, Pageable pageable) {
        UsuarioEntity usuario = usuarioService.get(usuarioId);
        return dashboardRepository.findByUsuario(usuario, pageable);
    }

    public Long getTotalDashboards() {
        return dashboardRepository.count();
    }
}