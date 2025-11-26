package com.example.tcg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String usuarioNombre;
    private String correo;
    private String password;
    private Boolean terminos;

    public Usuario() {
    }

    public Usuario(Integer id, String usuarioNombre, String correo, String password, Boolean terminos) {
        this.id = id;
        this.usuarioNombre = usuarioNombre;
        this.correo = correo;
        this.password = password;
        this.terminos = terminos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getTerminos() {
        return terminos;
    }

    public void setTerminos(Boolean terminos) {
        this.terminos = terminos;
    }
}