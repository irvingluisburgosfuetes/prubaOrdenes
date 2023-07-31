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

import com.burgos.app.ordenes.dto.ida.SucursalCreateDto;
import com.burgos.app.ordenes.dto.vuelta.SucursalListDto;
import com.burgos.app.ordenes.services.IService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/sucursales")
public class SucursalController {
	
	@Autowired
	IService<SucursalListDto, SucursalCreateDto> sucursalService;
	
	@PostMapping
	public Map<String,String> guardar(@RequestBody SucursalCreateDto c){
		System.out.println(c);
		sucursalService.guardar(c);
		Map<String, String> respuesta = new HashMap<>();
		respuesta.put("msg", "Sucursal guardada correctamente");
		return respuesta;
	}
	
	@GetMapping
	public List<SucursalListDto> listar(){
		return sucursalService.listar();
	}
	
	
	@GetMapping("/obtener/{id}")
	public SucursalListDto obtenerPorId(@PathVariable(name = "id") Long id) {
		Optional<SucursalListDto> venta = sucursalService.getById(id);
		if(venta.isPresent()) {
			return venta.get();
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/eliminar/")
	public Map<String,String> eliminar(@RequestParam(name ="id")Long id){
		sucursalService.eliminar(id);
		Map<String,String> respuesta = new HashMap<>();
		respuesta.put("msg","Sucursal eliminada correctamente");
		return respuesta;
	}
	
	
	@PutMapping("/actualizar/{id}")
	public Map<String,String> actualizar(@RequestBody SucursalCreateDto c, @PathVariable(name = "id") Long id)
	{
		Optional<SucursalListDto> sucursal = sucursalService.getById(id);
		if(sucursal.isPresent()) {
			c.setSucursalId(id);
			sucursalService.guardar(c);
		}else {
			throw new RuntimeException("La Sucursal no existe en la base de datos");
		}
		Map<String,String> respuesta = new HashMap<>();
		respuesta.put("msg","Sucursal actualizada correctamente");
		return respuesta;
	}

	
	

}
