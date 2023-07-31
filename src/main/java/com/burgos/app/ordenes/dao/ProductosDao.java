package com.burgos.app.ordenes.dao;

import org.springframework.data.repository.CrudRepository;

import com.burgos.app.ordenes.models.Producto;

public interface ProductosDao extends CrudRepository<Producto, Long>{
}
