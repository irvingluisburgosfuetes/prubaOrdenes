package com.burgos.app.ordenes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burgos.app.ordenes.dao.SucursalesDao;
import com.burgos.app.ordenes.dto.ida.SucursalCreateDto;
import com.burgos.app.ordenes.dto.vuelta.SucursalListDto;
import com.burgos.app.ordenes.models.Sucursal;

@Service
public class SucursalService implements IService<SucursalListDto, SucursalCreateDto>{

	@Autowired
	SucursalesDao sucursalesDao;
	
	
	@Override
	public List<SucursalListDto> listar() {
		// TODO Auto-generated method stub
		List<SucursalListDto> ventasDTO = new ArrayList<>();
		for(Sucursal v: sucursalesDao.findAll()) {
			ventasDTO.add(this.modeltoDto(v));
		}
		return ventasDTO;
	}

	@Override
	public Optional<SucursalListDto> getById(Long id) {
		// TODO Auto-generated method stub
		Optional<SucursalListDto> obj = Optional.empty();
		Optional<Sucursal> venta = sucursalesDao.findById(id);
		if(venta.isPresent()) {
			obj = Optional.of(this.modeltoDto(venta.get()));
		}
		return obj;
	}

	@Override
	public void guardar(SucursalCreateDto s) {
		// TODO Auto-generated method stub
		//proceso de conversion convertir un dto al modelo original
			Sucursal v = this.dtoToModel(s);
			sucursalesDao.save(v);
		}
	

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		sucursalesDao.deleteById(id);
	}
	
	

Sucursal dtoToModel(SucursalCreateDto sucursalDto) {
	Sucursal v = new Sucursal();
	v.setNombre(sucursalDto.getNombre());
	v.setSucursalId(sucursalDto.getSucursalId());
	return v;
}

//Metodo para convertir un modelo original en un venta list DTO
	SucursalListDto modeltoDto(Sucursal sucursal) {
	SucursalListDto sucursalDto = new SucursalListDto();
	sucursalDto.setNombre(sucursal.getNombre());
	sucursalDto.setSucursalId(sucursal.getSucursalId());
		return sucursalDto;
	}


}












