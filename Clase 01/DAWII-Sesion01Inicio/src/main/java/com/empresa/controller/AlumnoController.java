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

import com.empresa.entity.Alumno;
import com.empresa.service.AlumnoService;


@RestController
@RequestMapping("/rest/alumno")
public class AlumnoController {

	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping
	public ResponseEntity<List<Alumno>> listAllAlumno(){
		List<Alumno> list = alumnoService.listAlumno();
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	public ResponseEntity<Alumno> createAlumno(@RequestBody Alumno alumno){
		Alumno alu = alumnoService.createAlumno(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alu);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateAlumno(@PathVariable("id") int id, @RequestBody Alumno alumno){
		Alumno alu = alumnoService.getAlumno(id);
		
		if (null == alu) {
			return ResponseEntity.notFound().build();
		}
		alumno.setIdAlumno(id);
		alu = alumnoService.updateAlumno(alumno);
		return ResponseEntity.ok(alu);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Alumno> deleteAlumno(@PathVariable("id") int id){
		Alumno alu = alumnoService.getAlumno(id);
		
		if (null == alu) {
			return ResponseEntity.notFound().build();
		}
		alumnoService.deleteAlumno(id);
		return ResponseEntity.ok(alu);
	}
}
