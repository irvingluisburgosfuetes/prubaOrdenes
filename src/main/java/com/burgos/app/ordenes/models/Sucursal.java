package com.burgos.app.ordenes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter @Getter @ToString
@Table(name = "prueba_sucursales")
public class Sucursal {
	@Id
	@Column(name = "sucusal_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "CUST_SEQ_PRUEBA_S")
	@SequenceGenerator(sequenceName = "CUST_SEQ_PRUEBA_S",
	allocationSize = 1, name = "CUST_SEQ_PRUEBA_S")
	private Long sucursalId;
	
	@Column(name = "nombre")
	private String nombre;
}


