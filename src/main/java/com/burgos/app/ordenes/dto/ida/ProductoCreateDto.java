package com.burgos.app.ordenes.dto.ida;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductoCreateDto {
	private Long productoId;
	private Long ordenId;
	private String codigo;	
	private String descripcion;
	private Float precio;
}
