package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Alumno;
import com.empresa.service.AlumnoService;

@RestController
@RequestMapping("/rest/alumno")
public class AlumnoController {

	@Autowired
	private AlumnoService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Alumno>> listaAlumno(){
		List<Alumno> lista = service.listaAlumno();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<Optional<Alumno>> getAlumnoForId(@PathVariable("id") int id){
		Optional<Alumno> alu = service.buscarporID(id);
		if (alu.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(alu);
	}
	
	@GetMapping(value = "/dni/{dni}")
	public ResponseEntity<Optional<Alumno>> getAlumnoForDni(@PathVariable("dni") String dni){
		Optional<Alumno> alu = service.buscarPorDNI(dni);
		if (alu.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(alu);
	}
	

	@PostMapping
	@ResponseBody
	public ResponseEntity<Alumno> insertaAlumno(@RequestBody Alumno obj){
		if (obj == null) {
			return ResponseEntity.noContent().build();	
		}else {
			obj.setIdAlumno(0);
			Alumno objSalida = service.insertaActualizaAlumno(obj);
			return ResponseEntity.ok(objSalida);
		}
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<Alumno> actualizarAlumno(@RequestBody Alumno obj){
		if (obj == null) {
			return ResponseEntity.noContent().build();	
		}else {
			
			Optional<Alumno> optAlu = service.buscarporID(obj.getIdAlumno());
			if (optAlu.isEmpty()) {
				return ResponseEntity.noContent().build();
			} else {
				Alumno objSalida = service.insertaActualizaAlumno(obj);
				return ResponseEntity.ok(objSalida);
			}
		}
	}
	
	@DeleteMapping("/{idParam}")
	@ResponseBody
	public ResponseEntity<Alumno> eliminaAlumno(@PathVariable("idParam") Integer idAlumno){
		
		Optional<Alumno> optAlu = service.buscarporID(idAlumno);
		if (optAlu.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			service.eliminaPorID(idAlumno);
			Optional<Alumno> optAlumnoEli = service.buscarporID(idAlumno);
			if (optAlumnoEli.isEmpty()) {
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.internalServerError().build();
			}
		}
	}
	
}
