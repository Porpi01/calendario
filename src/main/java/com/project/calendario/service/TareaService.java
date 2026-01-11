package com.project.calendario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.calendario.entity.DashboardEntity;
import com.project.calendario.entity.TareaEntity;
import com.project.calendario.exception.ResourceNotFoundException;
import com.project.calendario.repository.TareaRepository;

@Service
public class TareaService {

    @Autowired
    TareaRepository oTareaRepository;

    @Autowired
    private DashboardService dashboardService;

    public TareaEntity get(Long id) {
        return oTareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado: " + id));
    }

    public Long create(TareaEntity TareaEntity) {
        // validar dashboard
        DashboardEntity dashboard = dashboardService.get(TareaEntity.getDashboard().getId());
        TareaEntity.setDashboard(dashboard);

        oTareaRepository.save(TareaEntity);
        return TareaEntity.getId();
    }

    public TareaEntity update(TareaEntity TareaEntity) {
        get(TareaEntity.getId());
        return oTareaRepository.save(TareaEntity);
    }

    public Long delete(Long id) {
        oTareaRepository.deleteById(id);
        return id;
    }

    public Page<TareaEntity> getByDashboard(Long dashboardId, Pageable pageable) {
        DashboardEntity dashboard = dashboardService.get(dashboardId);
        return oTareaRepository.findByDashboard(dashboard, pageable);
    }

    public Page<TareaEntity> getByUsuario(Long usuarioId, Pageable pageable) {
        return oTareaRepository.findByDashboardUsuarioId(usuarioId, pageable);
    }

    public Long countByDashboard(Long dashboardId) {
        DashboardEntity dashboard = dashboardService.get(dashboardId);
        return oTareaRepository.countByDashboard(dashboard);
    }

    public TareaEntity changeEstado(Long eventoId, Integer estado) {
        TareaEntity evento = get(eventoId);
        evento.setEstado(estado);
        return oTareaRepository.save(evento);

    }
}