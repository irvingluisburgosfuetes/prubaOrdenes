package com.burgos.app.ordenes.dto.ida;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class OrdenCreateDto {
	private Long ordenId;
	private Integer sucursalId;	
	private Date fecha;
	private Float total;
}
