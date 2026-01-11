package com.project.calendario.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.calendario.entity.TareaEntity;
import com.project.calendario.service.TareaService;

@RestController
@RequestMapping("/tareas")
public class TareaApi {

    @Autowired
    TareaService oTareaService;

    @GetMapping("/{id}")
    public ResponseEntity<TareaEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oTareaService.get(id));
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody TareaEntity TareaEntity) {
        return ResponseEntity.ok(oTareaService.create(TareaEntity));
    }

    @PutMapping("")
    public ResponseEntity<TareaEntity> update(@RequestBody TareaEntity TareaEntity) {
        return ResponseEntity.ok(oTareaService.update(TareaEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oTareaService.delete(id));
    }

    // eventos por dashboard
    @GetMapping("/dashboard/{dashboardId}")
    public ResponseEntity<Page<TareaEntity>> getEventosByDashboard(
            @PathVariable("dashboardId") Long dashboardId,
            Pageable pageable) {
        return ResponseEntity.ok(oTareaService.getByDashboard(dashboardId, pageable));
    }

    // eventos por usuario (a través de dashboards)
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Page<TareaEntity>> getEventosByUsuario(
            @PathVariable("usuarioId") Long usuarioId,
            Pageable pageable) {
        return ResponseEntity.ok(oTareaService.getByUsuario(usuarioId, pageable));
    }

    // contar eventos de un dashboard
    @GetMapping("/total/dashboard/{dashboardId}")
    public ResponseEntity<Long> countEventosByDashboard(@PathVariable Long dashboardId) {
        return ResponseEntity.ok(oTareaService.countByDashboard(dashboardId));
    }

    // cambiar estado (to do / doing / done)
    @PutMapping("/{id}/estado/{estado}")
    public ResponseEntity<TareaEntity> changeEstado(
            @PathVariable Long id,
            @PathVariable Integer estado) {
        return ResponseEntity.ok(oTareaService.changeEstado(id, estado));
    }
}

