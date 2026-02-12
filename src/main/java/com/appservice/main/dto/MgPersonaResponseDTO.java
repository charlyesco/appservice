package com.appservice.main.dto;

public class MgPersonaResponseDTO {

    private String id;
    private Long id_persona;
    private String nombre;
    private String apellido;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getId_persona() {
        return id_persona;
    }

    public void setId_persona(Long id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "MgPersonaResponseDTO [id=" + id + ", id_persona=" + id_persona + ", nombre=" + nombre + ", apellido="
                + apellido + "]";
    }
}
