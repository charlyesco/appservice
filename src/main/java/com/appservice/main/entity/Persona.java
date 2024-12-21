package com.appservice.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//IMPORTANTE anadir el nombre de la tabla
@Entity(name = "personas")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPersona;

	private String nomPersona;

	private String apellidoPersona;

	public Persona() {
	}

	public Persona(Integer idPersona, String nomPersona, String apellidoPersona) {
		this.idPersona = idPersona;
		this.nomPersona = nomPersona;
		this.apellidoPersona = apellidoPersona;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getNomPersona() {
		return nomPersona;
	}

	public void setNomPersona(String nomPersona) {
		this.nomPersona = nomPersona;
	}

	public String getApellidoPersona() {
		return apellidoPersona;
	}

	public void setApellidoPersona(String apellidoPersona) {
		this.apellidoPersona = apellidoPersona;
	}

	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nomPersona=" + nomPersona + ", apellidoPersona=" + apellidoPersona
				+ "]";
	}

}
