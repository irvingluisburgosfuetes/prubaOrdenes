package com.burgos.app.ordenes.dao;

import org.springframework.data.repository.CrudRepository;

import com.burgos.app.ordenes.models.Orden;

public interface OrdenesDao extends CrudRepository<Orden, Long> {

}
