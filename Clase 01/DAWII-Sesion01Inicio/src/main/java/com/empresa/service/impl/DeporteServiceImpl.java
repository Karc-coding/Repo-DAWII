package com.empresa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Deporte;
import com.empresa.repository.DeporteRepository;
import com.empresa.service.DeporteService;

@Service
public class DeporteServiceImpl implements DeporteService{

	@Autowired
	private DeporteRepository repo;
	
	@Override
	public List<Deporte> listDeporte() {
		return repo.findAll();
	}

	@Override
	public Deporte getDeporte(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Deporte createDeporte(Deporte deporte) {
		return repo.save(deporte);
	}

	@Override
	public Deporte updateDeporte(Deporte deporte) {
		return repo.save(deporte);
	}

	@Override
	public void deleteDeporte(int id) {
		repo.deleteById(id);
	}
}
