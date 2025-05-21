package service;

import pojos.Productos;
import repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Crear un nuevo producto
    public Productos crearProducto(Productos producto) {
        return productoRepository.save(producto);
    }

    // Obtener todos los productos
    public List<Productos> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    // Obtener producto por nombre
    public List<Productos> obtenerProductosPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Obtener producto por ID
    public Optional<Productos> obtenerProductoPorId(int id) {
        return productoRepository.findById(id);
    }

    // Actualizar un producto
    public Productos actualizarProducto(Productos producto) {
        return productoRepository.save(producto);
    }

    // Eliminar producto
    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }

    // Aumentar stock
    public void aumentarStock(int id) {
        Productos producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setStockActual(producto.getStockActual() + 1);
        productoRepository.save(producto);
    }

    // Disminuir stock
    public void disminuirStock(int id) {
        Productos producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setStockActual(producto.getStockActual() - 1);
        productoRepository.save(producto);
    }

    // Obtener todos (para inventario)
    public List<Productos> obtenerTodos() {
        return productoRepository.findAll();
    }

    // Obtener productos con stock mínimo
    public List<Productos> obtenerPorStockMinimo() {
    	return productoRepository.findProductosConStockMenorOIgualAlMinimo();
    }

    // Obtener productos deshabilitados
    public List<Productos> obtenerDeshabilitados() {
        return productoRepository.findByHabilitadoFalse();
    }
}
