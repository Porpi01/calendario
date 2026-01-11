package com.project.calendario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.calendario.entity.UsuarioEntity;
import com.project.calendario.exception.ResourceNotFoundException;
import com.project.calendario.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioEntity get(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + id));
    }

    public Long create(UsuarioEntity usuarioEntity) {
        usuarioRepository.save(usuarioEntity);
        return usuarioEntity.getId();
    }

    public UsuarioEntity update(UsuarioEntity usuarioEntity) {
        get(usuarioEntity.getId()); // valida existencia
        return usuarioRepository.save(usuarioEntity);
    }

    public Long delete(Long id) {
        usuarioRepository.deleteById(id);
        return id;
    }

    public Page<UsuarioEntity> getPage(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Long getTotalUsuarios() {
        return usuarioRepository.count();
    }
}