package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Alumno;

public interface AlumnoService {

	public abstract List<Alumno> listaAlumno();
	public abstract Alumno insertaActualizaAlumno(Alumno obj);
	public abstract Optional<Alumno>buscarporID(int idAlumno);
	public void eliminaPorID(int idAlumno);
	public abstract Optional<Alumno> buscarPorDNI(String dni);
}
