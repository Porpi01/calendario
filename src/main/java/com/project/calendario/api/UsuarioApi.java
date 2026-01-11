package com.project.calendario.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.calendario.entity.UsuarioEntity;
import com.project.calendario.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class UsuarioApi {

     @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(usuarioService.get(id));
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody UsuarioEntity usuarioEntity) {
        return ResponseEntity.ok(usuarioService.create(usuarioEntity));
    }

    @PutMapping("")
    public ResponseEntity<UsuarioEntity> update(@RequestBody UsuarioEntity usuarioEntity) {
        return ResponseEntity.ok(usuarioService.update(usuarioEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(usuarioService.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<UsuarioEntity>> getPage(Pageable pageable) {
        return ResponseEntity.ok(usuarioService.getPage(pageable));
    }
}
