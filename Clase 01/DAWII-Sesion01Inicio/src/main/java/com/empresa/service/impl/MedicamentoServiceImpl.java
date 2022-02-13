package com.empresa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Medicamento;
import com.empresa.repository.MedicamentoRepository;
import com.empresa.service.MedicamentoService;

@Service
public class MedicamentoServiceImpl implements MedicamentoService{

	@Autowired
	MedicamentoRepository repo;
	
	@Override
	public List<Medicamento> listMedicamento() {
		return repo.findAll();
	}

	@Override
	public Medicamento getMedicamento(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Medicamento createMedicamento(Medicamento medicamento) {
		return repo.save(medicamento);
	}

	@Override
	public Medicamento updateMedicamento(Medicamento medicamento) {
		return repo.save(medicamento);
	}

	@Override
	public void deleteMedicamento(Long id) {
		repo.deleteById(id);
	}
}
