package com.empresa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Alumno;
import com.empresa.repository.AlumnoRepository;
import com.empresa.service.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService{

	@Autowired
	public AlumnoRepository repo;
	
	@Override
	public List<Alumno> listAlumno() {
		return repo.findAll();
	}
	
	@Override
	public Alumno getAlumno(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Alumno createAlumno(Alumno alumno) {
		return repo.save(alumno);
	}

	@Override
	public Alumno updateAlumno(Alumno alumno) {
		return repo.save(alumno);
	}

	@Override
	public void deleteAlumno(int id) {
		repo.deleteById(id);
	}
}
