package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pojos.Movimientos;
import repository.MovimientosRepository;
import service.MovimientosService;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientosController {
	@Autowired
	private MovimientosRepository movRepository;

	@Autowired
	private MovimientosService movService;

	// Endpoint para crear nuevo albarán
	@PostMapping
	public ResponseEntity<Movimientos> crearMovimientos(@RequestBody Movimientos movi) {
		Movimientos movGuardado = movRepository.save(movi);
		return ResponseEntity.ok(movGuardado);
	}

	@GetMapping
	public List<Movimientos> getAllMovimientos() {
		return movService.getAll();
	}

	@GetMapping("/{id}")
	public List<Movimientos> obtenerMovimientosUser(@PathVariable int id) {
		return movService.obtenerProductoPorId(id);
	}
}
