package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("/nombre/{nombre}")
	public Productos obtenerPorNombre(@PathVariable String nombre) {
	    return productService.obtenerPorNombre(nombre);
	}

	@GetMapping("/id/{idProducto}")
	public Productos obtenerPorId(@PathVariable int idProducto) {
	    return productService.obtenerPorIdProducto(idProducto);
	}
	@PostMapping("/actualizar/productos")
	public String actualizarProductos(@RequestBody List<Productos> productos) {
	    boolean actualizados = productService.actualizarProductos(productos);
	    String mensajeR;
	    if(actualizados) {
	    	mensajeR="Productos actualizados correctamente";
	    }else {
	    	mensajeR="Error al actualizar productos";
	    }
	    
	    return mensajeR;
	       
	}
	@PostMapping("/añadir/producto")
	public String anadirProducto(@RequestBody Productos productos) {
	    boolean actualizados = productService.anadirProducto(productos);
	    String mensajeR;
	    if(actualizados) {
	    	mensajeR="Producto añadido correctamente";
	    	System.out.println(mensajeR);
	    }else {
	    	mensajeR="Error al añadir el producto";
	    	System.out.println(mensajeR);
	    }
	    
	    return mensajeR;
	       
	}
	@PostMapping("/editar/producto")
	public String editarProducto(@RequestBody Productos productos) {
	    boolean actualizados = productService.anadirProducto(productos);
	    String mensajeR;
	    if(actualizados) {
	    	mensajeR="Producto editado correctamente";
	    	System.out.println(mensajeR);
	    }else {
	    	mensajeR="Error al editar el producto";
	    	System.out.println(mensajeR);
	    }
	    
	    return mensajeR;
	       
	}
}
