package com.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Docente;
import com.empresa.repository.DocenteRepository;

@Service
public class DocenteServiceImpl implements DocenteService {

	@Autowired
	private DocenteRepository repository;

	@Override
	public List<Docente> listaDocente() {
		return repository.findAll();
	}

	@Override
	public Docente insertaActualizaDocente(Docente obj) {
		return repository.save(obj);
	}

	@Override
	public List<Docente> listaDocentePorNombreLike(String nombre) {
		return repository.findByNombreLike(nombre);
	}

	@Override
	public List<Docente> listaDocentePorDni(String dni) {
		return repository.findByDni(dni);
	}

	@Override
	public List<Docente> listaDocentePorDniAndNombreLike(String dni, String nombre) {
		return repository.findByDniAndNombreLike(dni, nombre);
	}

	@Override
	public List<Docente> listaDocentePorNombreDniQuery(String nombre, String dni) {
		return repository.listaPorNombreDni(nombre, dni);
	}

}
