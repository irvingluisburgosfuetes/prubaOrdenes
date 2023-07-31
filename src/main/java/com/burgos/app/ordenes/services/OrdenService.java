package com.burgos.app.ordenes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burgos.app.ordenes.dao.OrdenesDao;
import com.burgos.app.ordenes.dao.ProductosDao;
import com.burgos.app.ordenes.dao.SucursalesDao;
import com.burgos.app.ordenes.dto.ida.OrdenCreateDto;
import com.burgos.app.ordenes.dto.vuelta.OrdenListDto;
import com.burgos.app.ordenes.dto.vuelta.ProductoDto;
import com.burgos.app.ordenes.dto.vuelta.SucursalListDto;
import com.burgos.app.ordenes.models.Orden;
import com.burgos.app.ordenes.models.Producto;

@Service
public class OrdenService implements IService<OrdenListDto, OrdenCreateDto> {

	@Autowired
	ProductosDao productosDao;

	@Autowired
	OrdenesDao ordenesDao;
	
	@Autowired
	SucursalesDao sucursalesDao;
	
	
	@Override
	public List<OrdenListDto> listar() {
		// TODO Auto-generated method stub
		List<OrdenListDto> ventasDTO = new ArrayList<>();
		for(Orden v: ordenesDao.findAll()) {
			ventasDTO.add(this.modeltoDto(v));
		}
		return ventasDTO;
	}

	@Override
	public Optional<OrdenListDto> getById(Long id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Optional<OrdenListDto> obj = Optional.empty();
		Optional<Orden> venta = ordenesDao.findById(id);
		if(venta.isPresent()) {
			obj = Optional.of(this.modeltoDto(venta.get()));
		}
		return obj;
	}

	@Override
	public void guardar(OrdenCreateDto s) {
		// TODO Auto-generated method stub
		Orden v = this.dtoToModel(s);
		ordenesDao.save(v);
		
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		ordenesDao.deleteById(id);
	}
	
	Orden dtoToModel(OrdenCreateDto ordenDto) {
		Orden v = new Orden();
		v.setFecha(ordenDto.getFecha());
		v.setOrdenId(ordenDto.getOrdenId());
		v.setSucursalId(sucursalesDao.findById(Long.parseLong(ordenDto.getSucursalId().toString())).get());
		v.setTotal(ordenDto.getTotal());	
		return v;
		}

		//Metodo para convertir un modelo original en un venta list DTO
		OrdenListDto modeltoDto(Orden orden) {
			OrdenListDto ordenListDto = new OrdenListDto();			
			ordenListDto.setFecha(orden.getFecha());
			for(Producto producto : orden.getListProductos()) {
				ProductoDto pDto = new ProductoDto();
				pDto.setCodigo(producto.getCodigo());
				pDto.setDescripcion(producto.getDescripcion());
				pDto.setPrecio(producto.getPrecio());
				pDto.setProductoId(producto.getProductoId());
				ordenListDto.getListaProductos().add(pDto);
			}
			
			ordenListDto.setOrdenId(orden.getOrdenId());
			SucursalListDto sucursal = new SucursalListDto();
			sucursal.setNombre(orden.getSucursalId().getNombre());
			sucursal.setSucursalId(orden.getSucursalId().getSucursalId());
			ordenListDto.setTotal(orden.getTotal());
			ordenListDto.setSucursalId(sucursal);
		return ordenListDto;	
		}
}

















