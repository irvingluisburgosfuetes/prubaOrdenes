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

import com.burgos.app.ordenes.dto.ida.OrdenCreateDto;
import com.burgos.app.ordenes.dto.ida.ProductoCreateDto;
import com.burgos.app.ordenes.dto.vuelta.OrdenListDto;
import com.burgos.app.ordenes.dto.vuelta.ProductoDto;
import com.burgos.app.ordenes.services.IService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/ordenes")
public class OrdenesController {
	@Autowired
	IService<OrdenListDto, OrdenCreateDto> ordenService;
	
	@PostMapping
	public Map<String,String> guardar(@RequestBody OrdenCreateDto c){
		System.out.println(c);
		ordenService.guardar(c);
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "Orden guardado correctamente");
		return respuesta;
	}
	
	@GetMapping
	public List<OrdenListDto> listar(){
		String[] semana = {"LUNES","MARTES","MIERCOLES"};
		
		for(String dia : semana) {
			System.out.println("Hoy es " + dia);
		}
		
		return ordenService.listar();
	}
	
	
	@GetMapping("/obtener/{id}")
	public OrdenListDto obtenerPorId(@PathVariable(name = "id") Long id) {
		Optional<OrdenListDto> venta = ordenService.getById(id);
		if(venta.isPresent()) {
			return venta.get();
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/eliminar/")
	public Map<String,String> eliminar(@RequestParam(name ="id")Long id){
		ordenService.eliminar(id);
		Map<String,String> respuesta = new HashMap<>();
		respuesta.put("msg","Orden eliminada correctamente");
		return respuesta;
	}
	
	
	@PutMapping("/actualizar/{id}")
	public Map<String,String> actualizar(@RequestBody OrdenCreateDto c, @PathVariable(name = "id") Long id)
	{
		Optional<OrdenListDto> sucursal = ordenService.getById(id);
		if(sucursal.isPresent()) {
			c.setOrdenId(id);		
			ordenService.guardar(c);
		}else {
			throw new RuntimeException("EL Orden no existe en la base de datos");
		}
		Map<String,String> respuesta = new HashMap<>();
		respuesta.put("msg","Orden actualizada correctamente");
		return respuesta;
	}


}
