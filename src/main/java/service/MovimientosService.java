package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojos.Movimientos;
import pojos.Productos;
import repository.MovimientosRepository;

@Service
public class MovimientosService {
	@Autowired
	private MovimientosRepository movRep;

	public List<Movimientos> getAll() {
		return movRep.findAll();
	}

	public List<Movimientos> obtenerProductoPorId(int userId) {
		return movRep.findByUsuario(userId);

	}
}
