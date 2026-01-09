package com.project.calendario.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.calendario.entity.UsuarioEntity;
import com.project.calendario.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioApi {

    private final UsuarioService usuarioService;

    // Constructor
    public UsuarioApi(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 🔹 GET /usuarios → todos los usuarios
    @GetMapping
    public List<UsuarioEntity> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    // 🔹 GET /usuarios/{id} → usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 POST /usuarios → crear usuario
    @PostMapping
    public UsuarioEntity createUsuario(@RequestBody UsuarioEntity usuario) {
        return usuarioService.createUsuario(usuario);
    }

    // 🔹 PUT /usuarios/{id} → actualizar usuario
    @PutMapping("/{id}")
    public UsuarioEntity updateUsuario(@PathVariable Long id, @RequestBody UsuarioEntity usuario) {
        return usuarioService.updateUsuario(id, usuario);
    }

    // 🔹 DELETE /usuarios/{id} → borrar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
