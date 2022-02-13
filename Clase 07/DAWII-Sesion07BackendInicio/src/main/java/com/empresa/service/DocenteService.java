package com.empresa.service;

import java.util.List;

import com.empresa.entity.Docente;

public interface DocenteService {

	public abstract List<Docente> listaDocente();
	public abstract Docente insertaActualizaDocente(Docente obj);
	public abstract List<Docente> listaDocentePorNombreLike(String nombre);
	public abstract List<Docente> listaDocentePorDni(String dni);
	public abstract List<Docente> listaDocentePorDniAndNombreLike(String dni, String nombre);
	
	public abstract List<Docente> listaDocentePorNombreDniQuery(String nombre, String dni);
	
}
