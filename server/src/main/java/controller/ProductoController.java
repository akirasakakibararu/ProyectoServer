package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pojos.Productos;

import service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoService productService;

	@GetMapping
	public List<Productos> listarProductos() {
		return productService.obtenerTodos();
	}

	@GetMapping("/{nombre}")
	public Productos obtenerPorNombre(@PathVariable String nombre) {
		return productService.obtenerPorNombre(nombre);
	}

	@GetMapping("/{id}")
	public Productos obtenerPorId(@PathVariable int idProducto) {
		return productService.obtenerPorIdProducto(idProducto);
	}
}
