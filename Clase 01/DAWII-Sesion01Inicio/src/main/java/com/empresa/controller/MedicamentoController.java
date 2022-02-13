package com.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empresa.entity.Medicamento;
import com.empresa.service.MedicamentoService;

@Controller
@RequestMapping("/rest/medicamento")
public class MedicamentoController {

	@Autowired
	public MedicamentoService medicamentoService;
	
	@GetMapping
	public ResponseEntity<List<Medicamento>> listAllMedicamento(){
		List<Medicamento> list = medicamentoService.listMedicamento();
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	public ResponseEntity<Medicamento> createMedicamento(@RequestBody Medicamento medicamento){
		Medicamento med = medicamentoService.createMedicamento(medicamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(med);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateMedicamento(@PathVariable("id") Long id,
														@RequestBody Medicamento medicamento){
		Medicamento med = medicamentoService.getMedicamento(id);
		
		if (null == med) {
			return ResponseEntity.notFound().build();
		}
		medicamento.setId(id);
		med = medicamentoService.updateMedicamento(medicamento);
		return ResponseEntity.ok(med);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Medicamento> updateMedicamento(@PathVariable("id") Long id){
		Medicamento med = medicamentoService.getMedicamento(id);
		
		if (null == med) {
			return ResponseEntity.notFound().build();
		}
		medicamentoService.deleteMedicamento(id);
		return ResponseEntity.ok(med);
	}
	
}
