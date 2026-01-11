package com.project.calendario.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.calendario.entity.DashboardEntity;
import com.project.calendario.service.DashboardService;

@RestController
@RequestMapping("/dashboards")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class DashboardApi {

     @Autowired
    private DashboardService dashboardService;

   

    @GetMapping("/{id}")
    public ResponseEntity<DashboardEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(dashboardService.get(id));
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody DashboardEntity dashboardEntity) {
        return ResponseEntity.ok(dashboardService.create(dashboardEntity));
    }

    @PutMapping("")
    public ResponseEntity<DashboardEntity> update(@RequestBody DashboardEntity dashboardEntity) {
        return ResponseEntity.ok(dashboardService.update(dashboardEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(dashboardService.delete(id));
    }

    // dashboards por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Page<DashboardEntity>> getDashboardsByUsuario(
            @PathVariable("usuarioId") Long usuarioId,
            Pageable pageable) {
        return ResponseEntity.ok(dashboardService.getByUsuario(usuarioId, pageable));
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalDashboards() {
        return ResponseEntity.ok(dashboardService.getTotalDashboards());
    }
    
}
