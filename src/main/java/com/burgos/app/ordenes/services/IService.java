package com.burgos.app.ordenes.services;

import java.util.List;
import java.util.Optional;

public interface IService<T, S> {
	List<T> listar();
	Optional<T> getById(Long id);
	void guardar(S s);
	void eliminar(Long id);
}
