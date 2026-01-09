package com.project.calendario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.calendario.entity.UsuarioEntity;
import com.project.calendario.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository oUsuarioRepository;

    public List<UsuarioEntity> getAllUsuarios() {
        return oUsuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> getUsuarioById(Long id) {
        return oUsuarioRepository.findById(id);
    }

    public UsuarioEntity createUsuario(UsuarioEntity usuario) {
        return oUsuarioRepository.save(usuario);
    }

    public UsuarioEntity updateUsuario(Long id, UsuarioEntity usuario) {
        usuario.setId(id);
        return oUsuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        oUsuarioRepository.deleteById(id);
    }
}
