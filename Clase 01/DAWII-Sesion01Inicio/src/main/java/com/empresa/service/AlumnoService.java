package com.empresa.service;

import java.util.List;

import com.empresa.entity.Alumno;

public interface AlumnoService {

	public List<Alumno> listAlumno();
	public Alumno createAlumno(Alumno alumno);	
	public Alumno updateAlumno(Alumno alumno);	
	public void deleteAlumno(int id);
	public Alumno getAlumno(int id);
	
}
