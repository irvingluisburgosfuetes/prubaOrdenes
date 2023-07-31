package com.burgos.app.ordenes.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter @Getter @ToString
@Table(name = "prueba_Ordenes")
public class Orden {

	@Id
	@Column(name = "orden_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "CUST_SEQ_PRUEBA_O")
	@SequenceGenerator(sequenceName = "CUST_SEQ_PRUEBA_O",
	allocationSize = 1, name = "CUST_SEQ_PRUEBA_O")
	private Long ordenId;
	
	@JoinColumn(name = "sucursal_id")
	@ManyToOne
	private Sucursal sucursalId;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "total")
	private Float total;
	
	//Se invierte la relacion
	@OneToMany(mappedBy = "ordenId")
	private List<Producto> listProductos = new ArrayList<>();

}

