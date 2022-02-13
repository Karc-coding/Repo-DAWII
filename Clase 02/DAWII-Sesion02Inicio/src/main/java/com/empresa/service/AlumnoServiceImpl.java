package com.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Alumno;
import com.empresa.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService{

	@Autowired
	private AlumnoRepository repository;
	

	@Override
	public List<Alumno> listaAlumno() {
		return repository.findAll();
	}

	@Override
	public Alumno insertaActualizaAlumno(Alumno obj) {
		return repository.save(obj);
	}

	@Override
	public void eliminaPorID(int idAlumno) {
		repository.deleteById(idAlumno);
	}
	
	@Override
	public Optional<Alumno> buscarporID(int idAlumno) {
		return repository.findById(idAlumno);
	}

	@Override
	public Optional<Alumno> buscarPorDNI(String dni) {
		return repository.findByDni(dni);
	}
}