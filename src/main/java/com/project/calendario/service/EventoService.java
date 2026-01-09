package com.project.calendario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.calendario.entity.EventoEntity;
import com.project.calendario.entity.UsuarioEntity;
import com.project.calendario.repository.EventoRepository;
import com.project.calendario.repository.UsuarioRepository;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;

    public EventoService(EventoRepository eventoRepository, UsuarioRepository usuarioRepository) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // 🔹 Obtener todos los eventos
    public List<EventoEntity> getAllEventos() {
        return eventoRepository.findAll();
    }

    // 🔹 Obtener evento por id
    public Optional<EventoEntity> getEventoById(Long id) {
        return eventoRepository.findById(id);
    }

    // 🔹 Obtener eventos de un usuario
    public List<EventoEntity> getEventosByUsuario(Long usuarioId) {
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return usuario.getEventos();
    }

    // 🔹 Crear evento para un usuario
    public EventoEntity createEvento(Long usuarioId, EventoEntity evento) {
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        evento.setUsuario(usuario);
        return eventoRepository.save(evento);
    }

    // 🔹 Actualizar evento
    public EventoEntity updateEvento(Long id, EventoEntity evento) {
        evento.setId(id);
        return eventoRepository.save(evento);
    }

    // 🔹 Borrar evento
    public void deleteEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}
