package com.burgos.app.ordenes.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.burgos.app.ordenes.dto.ida.ProductoCreateDto;
import com.burgos.app.ordenes.dto.vuelta.ProductoDto;
import com.burgos.app.ordenes.services.IService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/productos")
public class ProductosController {
	@Autowired
	IService<ProductoDto, ProductoCreateDto> productoService;
	
	@PostMapping
	public Map<String,String> guardar(@RequestBody ProductoCreateDto c){
		System.out.println(c);
		productoService.guardar(c);
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "Producto guardado correctamente");
		return respuesta;
	}
	
	@GetMapping
	public List<ProductoDto> listar(){
		return productoService.listar();
	}
	
	
	@GetMapping("/obtener/{id}")
	public ProductoDto obtenerPorId(@PathVariable(name = "id") Long id) {
		Optional<ProductoDto> venta = productoService.getById(id);
		if(venta.isPresent()) {
			return venta.get();
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/eliminar/")
	public Map<String,String> eliminar(@RequestParam(name ="id")Long id){
		productoService.eliminar(id);
		Map<String,String> respuesta = new HashMap<>();
		respuesta.put("msg","Producto eliminada correctamente");
		return respuesta;
	}
	
	
	@PutMapping("/actualizar/{id}")
	public Map<String,String> actualizar(@RequestBody ProductoCreateDto c, @PathVariable(name = "id") Long id)
	{
		Optional<ProductoDto> sucursal = productoService.getById(id);
		if(sucursal.isPresent()) {
			c.setProductoId(id);
			productoService.guardar(c);
		}else {
			throw new RuntimeException("EL Producto no existe en la base de datos");
		}
		Map<String,String> respuesta = new HashMap<>();
		respuesta.put("msg","Producto actualizada correctamente");
		return respuesta;
	}	

}
