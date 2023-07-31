package com.burgos.app.ordenes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burgos.app.ordenes.dao.OrdenesDao;
import com.burgos.app.ordenes.dao.ProductosDao;
import com.burgos.app.ordenes.dto.ida.ProductoCreateDto;
import com.burgos.app.ordenes.dto.vuelta.ProductoDto;
import com.burgos.app.ordenes.models.Producto;

@Service
public class ProductoService  implements IService<ProductoDto, ProductoCreateDto>{

	@Autowired
	ProductosDao productosDao;
	
	@Autowired
	OrdenesDao ordenesDao;
	
	@Override
	public List<ProductoDto> listar() {
		// TODO Auto-generated method stub
		List<ProductoDto> ventasDTO = new ArrayList<>();
		for(Producto v: productosDao.findAll()) {
			ventasDTO.add(this.modeltoDto(v));
		}
		return ventasDTO;
	}

	@Override
	public Optional<ProductoDto> getById(Long id) {
		// TODO Auto-generated method stub
		Optional<ProductoDto> obj = Optional.empty();
		Optional<Producto> venta = productosDao.findById(id);
		if(venta.isPresent()) {
			obj = Optional.of(this.modeltoDto(venta.get()));
		}
		return obj;
	}

	@Override
	public void guardar(ProductoCreateDto s) {
		// TODO Auto-generated method stub
		//proceso de conversion convertir un dto al modelo original
			Producto v = this.dtoToModel(s);
			productosDao.save(v);
		}
	

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		productosDao.deleteById(id);
		
	}
	
	

Producto dtoToModel(ProductoCreateDto productoDto) {
	Producto v = new Producto();
	v.setCodigo(productoDto.getCodigo());
	v.setDescripcion(productoDto.getDescripcion());
	v.setOrdenId(ordenesDao.findById(Long.parseLong(productoDto.getOrdenId().toString())).get());
	v.setPrecio(productoDto.getPrecio());
	v.setProductoId(productoDto.getProductoId());
	return v;
}

//Metodo para convertir un modelo original en un venta list DTO
	ProductoDto modeltoDto(Producto producto) {
		ProductoDto productoDto = new ProductoDto();
		productoDto.setCodigo(producto.getCodigo());
		productoDto.setDescripcion(producto.getDescripcion());
		productoDto.setPrecio(producto.getPrecio());
		productoDto.setProductoId(producto.getProductoId());
	return productoDto;	
	}


}

