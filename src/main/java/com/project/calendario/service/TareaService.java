package com.project.calendario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.calendario.entity.TareaEntity;
import com.project.calendario.entity.UsuarioEntity;
import com.project.calendario.repository.TareaRepository;
import com.project.calendario.repository.UsuarioRepository;

@Service
public class TareaService {

    private final TareaRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;

    public TareaService(TareaRepository eventoRepository, UsuarioRepository usuarioRepository) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // 🔹 Obtener todos los eventos
    public List<TareaEntity> getAllEventos() {
        return eventoRepository.findAll();
    }

    // 🔹 Obtener evento por id
    public Optional<TareaEntity> getEventoById(Long id) {
        return eventoRepository.findById(id);
    }

    // 🔹 Obtener eventos de un usuario
    public List<TareaEntity> getEventosByUsuario(Long usuarioId) {
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return usuario.getEventos();
    }

    // 🔹 Crear evento para un usuario
    public TareaEntity createEvento(Long usuarioId, TareaEntity evento) {
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        evento.setUsuario(usuario);
        return eventoRepository.save(evento);
    }

    // 🔹 Actualizar evento
    public TareaEntity updateEvento(Long id, TareaEntity evento) {
        evento.setId(id);
        return eventoRepository.save(evento);
    }

    // 🔹 Borrar evento
    public void deleteEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}
