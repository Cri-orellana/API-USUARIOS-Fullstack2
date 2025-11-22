package com.example.tcg.service;

import com.example.tcg.model.Usuario;
import com.example.tcg.model.Entity.UsuarioEntity;
import com.example.tcg.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repositorioUsuario;

    private Usuario convertirAUsuario(UsuarioEntity entidad) {
        if (entidad == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(entidad.getId());
        usuario.setUsuarioNombre(entidad.getUsuarioNombre());
        usuario.setCorreo(entidad.getCorreo());
        usuario.setPassword(entidad.getPassword());
        usuario.setTerminos(entidad.getTerminos());
        return usuario;
    }

    private UsuarioEntity convertirAEntidadUsuario(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioEntity entidad = new UsuarioEntity();
        entidad.setId(usuario.getId());
        entidad.setUsuarioNombre(usuario.getUsuarioNombre());
        entidad.setCorreo(usuario.getCorreo());
        entidad.setPassword(usuario.getPassword());
        entidad.setTerminos(usuario.getTerminos());
        return entidad;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return repositorioUsuario.findAll()
                .stream()
                .map(this::convertirAUsuario)
                .collect(Collectors.toList());
    }

    public Usuario obtenerUsuarioPorId(Integer id) {
        return repositorioUsuario.findById(id)
                .map(this::convertirAUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con ID: " + id));
    }

    public Usuario crearUsuario(Usuario usuario) {
        UsuarioEntity entidadAGuardar = convertirAEntidadUsuario(usuario);
        UsuarioEntity entidadGuardada = repositorioUsuario.save(entidadAGuardar);
        return convertirAUsuario(entidadGuardada);
    }

    public Usuario actualizarUsuario(Usuario usuario) {
        if (!repositorioUsuario.existsById(usuario.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se puede actualizar. ID no existe: " + usuario.getId());
        }
        UsuarioEntity entidadAActualizar = convertirAEntidadUsuario(usuario);
        UsuarioEntity entidadActualizada = repositorioUsuario.save(entidadAActualizar);
        return convertirAUsuario(entidadActualizada);
    }

    public void eliminarUsuario(Integer id) {
        if (!repositorioUsuario.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede eliminar. ID no existe: " + id);
        }
        repositorioUsuario.deleteById(id);
    }
}
