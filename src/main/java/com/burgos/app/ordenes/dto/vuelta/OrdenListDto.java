package com.burgos.app.ordenes.dto.vuelta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class OrdenListDto {
	private Long ordenId;
	private SucursalListDto sucursalId;
	private Date fecha;	
	private Float total;
	private List<ProductoDto> listaProductos = new ArrayList<>();
}
