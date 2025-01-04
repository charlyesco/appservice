package com.appservice.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//IMPORTANTE anadir el nombre de la tabla
@Entity(name = "personas")
public class PersonaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nombre;

	private String apellido;

	public PersonaEntity() {
	}

	public PersonaEntity(Integer idPersona, String nomPersona, String apellidoPersona) {
		this.id = idPersona;
		this.nombre = nomPersona;
		this.apellido = apellidoPersona;
	}

	public Integer getIdPersona() {
		return id;
	}

	public void setIdPersona(Integer idPersona) {
		this.id = idPersona;
	}

	public String getNomPersona() {
		return nombre;
	}

	public void setNomPersona(String nomPersona) {
		this.nombre = nomPersona;
	}

	public String getApellidoPersona() {
		return apellido;
	}

	public void setApellidoPersona(String apellidoPersona) {
		this.apellido = apellidoPersona;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}

}
