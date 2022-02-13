package com.empresa.service;

import java.util.List;

import com.empresa.entity.Medicamento;

public interface MedicamentoService {

	public List<Medicamento> listMedicamento();
	public Medicamento getMedicamento(Long id);
	public Medicamento createMedicamento(Medicamento medicamento);
	public Medicamento updateMedicamento(Medicamento medicamento);
	public void deleteMedicamento(Long id);
}
