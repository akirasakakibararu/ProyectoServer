package com.example.server;

import org.springframework.data.jpa.repository.JpaRepository;

import pojos.Productos;


public interface ProductosRepository extends JpaRepository <Productos , Long>{
	
	Productos findByNombre(String Nombre);
	Productos findByIdProducto(int idProducto);

}
