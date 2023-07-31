package com.burgos.app.ordenes.dao;

import org.springframework.data.repository.CrudRepository;

import com.burgos.app.ordenes.models.Sucursal;

public interface SucursalesDao extends CrudRepository<Sucursal, Long>{

}
