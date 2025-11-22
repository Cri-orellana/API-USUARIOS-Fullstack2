package com.example.tcg.controller;

import com.example.tcg.model.Usuario;
import com.example.tcg.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Administración Usuarios", description = "CRUD Usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService servicioUsuario;

    @GetMapping
    @Operation(summary = "Listado de Usuarios Registrados")
    public List<Usuario> obtenerTodosLosUsuarios() {
        return servicioUsuario.obtenerTodosLosUsuarios();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por id")
    public Usuario obtenerUsuarioPorId(@PathVariable Integer id) {
        return servicioUsuario.obtenerUsuarioPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Añadir nuevo Usuario")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return servicioUsuario.crearUsuario(usuario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario existente")
    public Usuario actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return servicioUsuario.actualizarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Borrar Usuario")
    public void eliminarUsuario(@PathVariable Integer id) {
        servicioUsuario.eliminarUsuario(id);
    }
}