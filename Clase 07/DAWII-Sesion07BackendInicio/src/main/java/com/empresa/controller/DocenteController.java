package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Docente;
import com.empresa.entity.Filtro;
import com.empresa.service.DocenteService;
import com.empresa.util.Constantes;

@RestController
@RequestMapping("/rest/docente")
@CrossOrigin(origins = "http://localhost:4200")
public class DocenteController {

	@Autowired
	private DocenteService docenteService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Docente>> listaDocente() {
		List<Docente> lista = docenteService.listaDocente();
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaDocente(@RequestBody Docente obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Docente objSalida = docenteService.insertaActualizaDocente(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/forName/{name}")
	@ResponseBody
	public ResponseEntity<List<Docente>> listaDocentePorNombre(@PathVariable("name") String name) {
		List<Docente> lista = docenteService.listaDocentePorNombreLike(name + "%");
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/forDni/{dni}")
	@ResponseBody
	public ResponseEntity<List<Docente>> listaDocentePorDni(@PathVariable("dni") String dni) {
		List<Docente> lista = docenteService.listaDocentePorDni(dni);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/forNameDni/{name}/{dni}")
	@ResponseBody
	public ResponseEntity<List<Docente>> listaDocentePorNombreAndDni(@PathVariable("name") String name, @PathVariable("dni") String dni) {
		List<Docente> lista = docenteService.listaDocentePorDniAndNombreLike(dni, name + "%");
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/forNameDniQuery/{name}/{dni}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaDocentePorNombreDni(@PathVariable("name") String name, @PathVariable("dni") String dni) {
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Docente> lista = docenteService.listaDocentePorNombreDniQuery(name + "%", dni);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("message", "No existen datos");
			} else {
				salida.put("lista", lista);
			}
		} catch (Exception e) {
			salida.put("message", "Error en la salida " + e.getMessage());
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/forNameDniParamQuery")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaDocentePorNombreDniConParametros(
					@RequestParam(value = "name") String name, @RequestParam(value = "dni") String dni) {
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Docente> lista = docenteService.listaDocentePorNombreDniQuery(name + "%", dni);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("message", "No existen datos");
			} else {
				salida.put("lista", lista);
			}
		} catch (Exception e) {
			salida.put("message", "Error en la salida " + e.getMessage());
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/forNameDniJson")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaDocentePorNombreDniJson(@RequestBody Filtro filtro) {
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Docente> lista = docenteService.listaDocentePorNombreDniQuery(filtro.getName() + "%", filtro.getDni());
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("message", "No existen datos");
			} else {
				salida.put("lista", lista);
			}
		} catch (Exception e) {
			salida.put("message", "Error en la salida " + e.getMessage());
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(salida);
	}

}
