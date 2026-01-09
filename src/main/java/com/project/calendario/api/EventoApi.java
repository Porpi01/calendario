package com.project.calendario.api;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.calendario.entity.EventoEntity;
import com.project.calendario.service.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoApi {

    private final EventoService eventoService;

    public EventoApi(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // 🔹 GET /eventos → todos los eventos
    @GetMapping
    public List<EventoEntity> getAllEventos() {
        return eventoService.getAllEventos();
    }

    // 🔹 GET /eventos/{id} → evento por id
    @GetMapping("/{id}")
    public ResponseEntity<EventoEntity> getEventoById(@PathVariable Long id) {
        return eventoService.getEventoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 GET /eventos/usuario/{usuarioId} → eventos de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public List<EventoEntity> getEventosByUsuario(@PathVariable Long usuarioId) {
        return eventoService.getEventosByUsuario(usuarioId);
    }

    // 🔹 POST /eventos/usuario/{usuarioId} → crear evento para usuario
    @PostMapping("/usuario/{usuarioId}")
    public EventoEntity createEvento(@PathVariable Long usuarioId, @RequestBody EventoEntity evento) {
        return eventoService.createEvento(usuarioId, evento);
    }

    // 🔹 PUT /eventos/{id} → actualizar evento
    @PutMapping("/{id}")
    public EventoEntity updateEvento(@PathVariable Long id, @RequestBody EventoEntity evento) {
        return eventoService.updateEvento(id, evento);
    }

    // 🔹 DELETE /eventos/{id} → borrar evento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
}

