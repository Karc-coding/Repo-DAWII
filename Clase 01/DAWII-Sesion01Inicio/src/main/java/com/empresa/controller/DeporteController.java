package com.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Deporte;
import com.empresa.service.DeporteService;

@RestController
@RequestMapping("/rest/deporte")
public class DeporteController {

	@Autowired
	private DeporteService deporteService;
	
	@GetMapping
	public ResponseEntity<List<Deporte>> listAllDeporte(){
		List<Deporte> list = deporteService.listDeporte();
		
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	public ResponseEntity<Deporte> createDeporte(@RequestBody Deporte deporte){
		Deporte dep = deporteService.createDeporte(deporte);
		return ResponseEntity.status(HttpStatus.CREATED).body(dep);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateDeporte(@PathVariable("id") int id, @RequestBody Deporte deporte){
		
		Deporte dep = deporteService.getDeporte(id);
		
		if (null == dep) {
			return ResponseEntity.notFound().build();
		}
		deporte.setIdDeporte(id);
		dep = deporteService.updateDeporte(deporte);
		return ResponseEntity.ok(dep);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Deporte> deleteDeporte(@PathVariable("id") int id){
		Deporte dep = deporteService.getDeporte(id);
		
		if (null == dep) {
			return ResponseEntity.notFound().build();
		}
		deporteService.deleteDeporte(id);
		return ResponseEntity.ok(dep);
	}
	
}
