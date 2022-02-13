package com.empresa.service;

import java.util.List;

import com.empresa.entity.Deporte;

public interface DeporteService {

	public List<Deporte> listDeporte();
	public Deporte createDeporte(Deporte deporte);
	public Deporte updateDeporte(Deporte deporte);
	public void deleteDeporte(int id);
	public Deporte getDeporte(int id);
}
