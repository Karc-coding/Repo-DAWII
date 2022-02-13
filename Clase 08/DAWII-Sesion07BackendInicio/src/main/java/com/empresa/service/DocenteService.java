package com.empresa.service;

import java.util.List;

import com.empresa.entity.Docente;

public interface DocenteService {

	public abstract List<Docente> listaDocente();

	public abstract Docente insertaActualizaDocente(Docente obj);
	
	public abstract List<Docente> consultaDocente(String name, String dni, int idUbigeo);

}
