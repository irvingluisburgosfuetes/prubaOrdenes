package com.burgos.app.ordenes.dto.vuelta;


import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ProductoDto {
	private Long productoId;
	private String codigo;
	private String descripcion;
	private Float precio;
}
