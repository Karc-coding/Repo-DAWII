package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entity.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {

	@Query("select d from Docente d where ( :p_nom is '' or d.nombre like :p_nom ) and "
									 + "  ( :p_dni is '' or d.dni = :p_dni ) and "
									 + "  ( :p_ubi is 0 or d.ubigeo.idUbigeo = :p_ubi)" )
	public List<Docente> consultaDocente(@Param("p_nom") String name,
										@Param("p_dni") String dni,
										@Param("p_ubi") int idUbigeo);
	
}


