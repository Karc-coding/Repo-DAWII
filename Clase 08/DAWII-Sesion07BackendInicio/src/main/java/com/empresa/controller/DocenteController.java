package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Docente;
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
	
	@GetMapping("/queryDocenteForParameters")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> consultaDocente(@RequestParam(name = "name", defaultValue = "", required = false) String name,
																@RequestParam(name = "dni", defaultValue = "", required = false) String dni,
																@RequestParam(name = "idUbigeo", defaultValue = "0", required = false) int idUbigeo){
		Map<String, Object> salida = new HashMap<>();
		
		List<Docente> lista = docenteService.consultaDocente("%" + name + "%", dni, idUbigeo);
		if (CollectionUtils.isEmpty(lista)) {
			salida.put("mensaje", "No existen Docentes en la consulta");
		} else {
			salida.put("list", lista);
			salida.put("mensaje", "Existen " + lista.size() + " docente");
		}
		return ResponseEntity.ok(salida);
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

}
