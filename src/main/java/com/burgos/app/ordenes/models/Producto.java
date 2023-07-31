package com.burgos.app.ordenes.models;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
@Table(name = "prueba_productos")
public class Producto {
	@Id
	@Column(name = "producto_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "CUST_SEQ_PRUEBA")
	@SequenceGenerator(sequenceName = "CUST_SEQ_PRUEBA",
	allocationSize = 1, name = "CUST_SEQ_PRUEBA")
	private Long productoId;
	
	@JoinColumn(name = "orden_id")
	@ManyToOne
	private Orden ordenId;
	
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "precio")
	private Float precio;
}
