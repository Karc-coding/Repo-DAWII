package com.empresa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "medicamento")
public class Medicamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMedicamento")
	private Long id;
	
	@Column(name = "nombre", nullable = false)
	private String name;
	
	@Column(name = "precio")
	private double price;
	
	@Column(name = "stock")
	private int stock;
	
	@Column(name = "laboratorio", nullable = false)
	private String laboratory;
	
}
